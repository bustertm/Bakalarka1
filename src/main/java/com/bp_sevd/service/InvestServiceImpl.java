package com.bp_sevd.service;

import com.bp_sevd.model.Appliance;
import com.bp_sevd.model.Household;
import com.bp_sevd.model.Location;
import com.bp_sevd.model.consumption.Example_consumption;
import com.bp_sevd.model.consumption.Example_type;
import com.bp_sevd.model.production.FVE_configurations;
import com.bp_sevd.model.production.FVE_production;
import com.bp_sevd.model.production.Investment;
import com.bp_sevd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Martin on 24.04.2017.
 */
@Service("investService")
public class InvestServiceImpl implements InvestService{

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private Example_consumptionRepository example_consumptionRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Autowired
    private ProductionRepository productionRepository;

    @Autowired
    private FVE_configurationRepository fve_configurationRepository;


    public Location findByID(int id){
        return locationRepository.findOne(id);
    }

    public void addLocation(Household household, Location location){

        location = findByID(location.getId());
        household.setLocation(location);
        householdRepository.save(household);
    }

    public List<Location> getLocations(){
        return locationRepository.findByOrderBySunIntensity();
    }


    public Investment findBestProduction(Household household) throws ParseException {
        Example_type e_type = household.getExample_id();        //zistim ktora vzorova domacnost patri k uzivatelovej
        Appliance appliance=household.getAppliance();

        java.sql.Date start=java.sql.Date.valueOf("2014-03-19");        //zaciatok doleziteho obdobia vyroby elektriny
        java.sql.Date end=java.sql.Date.valueOf("2014-09-7");           //koniec doleziteho obdobia (zvysok roka je vyroba miziva a kazila by vypocty)

        List<Example_consumption> example_consumptionList = example_consumptionRepository.findByTypeAndDateBetweenOrderByDateAsc(e_type,start,end); //vytiahnem data spotreby zoradene podla datumu v rozmedzi datumov
        List<FVE_production> fve_productions = productionRepository.getKeyProductions();       //vytiahnem data o vyrobe pre obdobie spominane vyssie

        Double spotreba=0.0, vyroba=0.0, rozdiel=0.0, vykon=0.0, celok=0.0, efektivita=0.0;
        Double vyroba_sum=0.0, prebytok_sum=0.0, prev_prebytok=0.0, prev_vyroba=0.0;

        Integer i=0, pocetPanelov=0;
        boolean founded=false;

        double radiation=household.getLocation().getSunIntensity();     //zistim polohu domacnosti a z toho slnecne ziarenie
        radiation/=1000;    //hodnoty od 1000 do 1300 takze budem moct vyrobu prenasobit 1 az 1.3 krat vyrobu

        while(founded!=true){       //while cyklus zvysujuci pocet panelov az kym nenajdeme ten spravny
            pocetPanelov++;
            vykon+=0.25;
            i=0;

            for (Example_consumption e_con:example_consumptionList)     //pre kazdy den vo vybranom obdobi
            {
                spotreba+=getRealOverall(e_con,appliance);
                vyroba+=fve_productions.get(i).getProduction();
                i++;

                if(i%7==0){

                    rozdiel=spotreba-vyroba*(vykon*radiation);      //rozdiel medzi vyrobou a spotrebou energie
                    vyroba_sum+=vyroba*(vykon*radiation);           //ukladam celkovu vyrobu
                    if(rozdiel<0) prebytok_sum+= -rozdiel;           //ak sme vyrobili viac ako spotrebovali
                    vyroba=spotreba=0.0;

                    celok+=rozdiel;                     //sucet vsetkych rozdielov, pre porovnanie
                }
            }

            if (celok<0) {      //celkovy prebytok bol vacsi ako nedostatok

                founded=true;
                efektivita=(1-(prev_prebytok/prev_vyroba))*100;     //efektivita vyuzitia vyrobenej energie
                pocetPanelov--;    //beriem predchadzajuci pocet lebo tento je uz nevhodny
            }
            else{

                celok=0.0;
                prev_prebytok=prebytok_sum;     //chcem si pamatat predchadzajuce
                prev_vyroba=vyroba_sum;
                prebytok_sum=vyroba_sum=0.0;
            }
        }


        FVE_configurations fve_configurations;      //v databaze mame 3 konfiguracie elektrarne, mala, stredna a velka elektraren, vyberiem podla poctu panelov
        if(pocetPanelov<11)
            fve_configurations=fve_configurationRepository.findByNameStartingWith("small");
        else if(pocetPanelov<16)
            fve_configurations=fve_configurationRepository.findByNameStartingWith("medium");
        else
            fve_configurations=fve_configurationRepository.findByNameStartingWith("large");

        Investment investment = new Investment(fve_configurations);      //vytvorim objekt investicie pre dalsiu metodu findInvestment
        investment.setPocet_panelov(pocetPanelov);
        investment.setEffectivity(efektivita);
        investment.setVykon(pocetPanelov*0.25);


        return investment;
    }

    public Investment findInvestment(Double energy_price, Double dotacia, Household household, Investment investment){

        List<FVE_production> productionYear=productionRepository.getAllProductions();       //celorocna vyroba
        FVE_configurations fve_configurations = investment.getConfiguration_id();

        Double avgConsumption= household.getCounted_overall()/365;
        Double celkova_vyroba=0.0,price=0.0;

        int batteryNumber=(int)(avgConsumption/2);          //pocet instalovaneho vykonu v bateriach je polka priemernej dennej spotreby
        int pocetPanelov= investment.getPocet_panelov();


        price+=fve_configurations.getFVE_panel_price()*pocetPanelov;        //cena za panely
        price+=fve_configurations.getBattery_price()*batteryNumber;         //cena za baterie
        price+=fve_configurations.getInvertor_charger_price();              //cena za menic a nabijac
        price+=fve_configurations.getOther_price()-dotacia;                 //dalsie naklady za pracu a material - dotacia ak nejaka je

        investment.setVykon_baterie(batteryNumber);
        investment.setDotacia(dotacia);
        investment.setEnergy_price(energy_price);

        for (FVE_production item:productionYear){

            celkova_vyroba+=item.getProduction()*((household.getLocation().getSunIntensity()/1000)* investment.getVykon());      //produkcia sa udava na instalovany kWp a panel ma 0.25kWp
        }

        celkova_vyroba*= investment.getEffectivity()/100;       //beriem len vyuzitu energiu do vypoctou navratnosti

        investment.setNavratnost(price/(celkova_vyroba*energy_price));
        investment.setCena(price);


        return investment;

    }


    private Double getRealOverall(Example_consumption item, Appliance appliance){       //sluzi na odstranenie spotrebicov ktore su navyse, vzorova ma ale pouzivatelova nie
        Double vysledok;
        vysledok=item.getOverall();


        if(appliance.getOven()>0){
            vysledok-=item.getOven();     //odcitam z celkovej spotreby
            vysledok+=(item.getOven()*appliance.getOven());  //pricitam prenasobene poctom
        }
        else vysledok-=item.getOven();         //odcitam spotrebic z celkovej kedze ho nemame

        if(appliance.getFridge()>0){
            vysledok-=item.getFridge();
            vysledok+=appliance.getFridge()*item.getFridge();

        }
        else vysledok-=item.getFridge();

        if(appliance.getDishwasher()>0){
            vysledok-=item.getDishwasher();
            vysledok+=appliance.getDishwasher()*item.getDishwasher();

        }
        else vysledok-=item.getDishwasher();

        if(appliance.getMicrowave()>0){
            vysledok-=item.getMicrowave();
            vysledok+=appliance.getMicrowave()*item.getMicrowave();

        }
        else vysledok-=item.getMicrowave();

        if(appliance.getWashingmachine()>0){
            vysledok-=item.getWashingmachine();
            vysledok+=appliance.getWashingmachine()*item.getWashingmachine();

        }
        else vysledok-=item.getWashingmachine();

        if(appliance.getDryer()>0){
            vysledok-=item.getDryer();
            vysledok+=appliance.getDryer()*item.getDryer();

        }
        else vysledok-=item.getDryer();

        if(appliance.getBoiler()>0){
            vysledok-=item.getBoiler();
            vysledok+=appliance.getBoiler()*item.getBoiler();

        }
        else vysledok-=item.getBoiler();

        if(appliance.getAircondition()>0){
            vysledok-=item.getAircondition();
            vysledok+=appliance.getAircondition()*item.getAircondition();

        }
        else vysledok-=item.getAircondition();

        if(appliance.getYakuza()>0){
            vysledok-=item.getYakuza();
            vysledok+=appliance.getYakuza()*item.getYakuza();

        }
        else vysledok-=item.getYakuza();


        return vysledok;
    }




}
