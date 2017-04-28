package com.bakalarka1.service;

import com.bakalarka1.model.Appliance;
import com.bakalarka1.model.Household;
import com.bakalarka1.model.Location;
import com.bakalarka1.model.User;
import com.bakalarka1.model.consumption.Example_consumption;
import com.bakalarka1.model.consumption.Example_type;
import com.bakalarka1.model.consumption.Monthly_consumption;
import com.bakalarka1.model.production.FVE_configurations;
import com.bakalarka1.model.production.FVE_production;
import com.bakalarka1.model.production.Investition;
import com.bakalarka1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        return locationRepository.findAll();
    }


    public Investition findBestProduction(Household household) throws ParseException {
        Example_type e_type = household.getExample_id();        //zistim ktora vzorova domacnost patri k uzivatelovej
        Appliance appliance=household.getAppliance();

       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date start=java.sql.Date.valueOf("2014-03-19");
        java.sql.Date end=java.sql.Date.valueOf("2014-09-7");

       // Date end= sdf.parse("2015-10-01");

       // List<Example_consumption> example_consumptionList = example_consumptionRepository.findByTypeAndDateBetweenOrderByDateAsc(e_type,start,end); //vytiahnem data spotreby zoradene podla datumu
        List<Example_consumption> example_consumptionList = example_consumptionRepository.findByTypeAndDateBetweenOrderByDateAsc(e_type,start,end); //vytiahnem data spotreby zoradene podla datumu
        List<FVE_production> fve_productions = productionRepository.getProductions();
        Double spotreba=0.0, vyroba=0.0, rozdiel=0.0, vykon=0.0, celok=0.0;
        Integer i=0, pocetPanelov=0;
        Double efektivita=0.0;
        boolean founded=false;
        Double vyroba_sum=0.0, prebytok_sum=0.0, prev_prebytok=0.0, prev_vyroba=0.0;
        double radiation=household.getLocation().getSun_intensity();
        radiation/=1000;    //hodnoty od 1000 do 1300 takze budem moct vyrobu prenasobit 1 az 1.3 krat

        while(founded!=true){
            pocetPanelov++;
            vykon+=0.25;
            i=0;

            for (Example_consumption e_con:example_consumptionList)
            {
                spotreba+=getRealOverall(e_con,appliance);
                vyroba+=fve_productions.get(i).getProduction();


                i++;

                if(i%7==0){

                    rozdiel=spotreba-vyroba*(vykon*radiation);      //1.1 lebo data s ktorymi porovnavam su z 1100 radiacie
                    vyroba_sum+=vyroba*(vykon*radiation);
                    if(rozdiel<0) prebytok_sum+= -rozdiel;           //ak sme vyrobili viac ako spotrebovali
                    vyroba=spotreba=0.0;
                 //   efektivity[k]=rozdiel;

                    celok+=rozdiel;
                }





            }
            if (celok<0) {

                founded=true;
                efektivita=(1-(prev_prebytok/prev_vyroba))*100;
                System.out.println("Spravna velkost instalacie je: "+(vykon)+"kW");
                pocetPanelov--;    //beriem predchadzajuci
            }

            else{
                System.out.println("Vysledny rozdiel vyroby a spotreby je: "+celok);
                celok=0.0;
                prev_prebytok=prebytok_sum;     //chcem si pamatat predchadzajuce
                prev_vyroba=vyroba_sum;
                prebytok_sum=vyroba_sum=0.0;

            }


           // System.out.println("Efektivita: "+(1-pom/pom2));

        }


        FVE_configurations fve_configurations;
        if(pocetPanelov<11)
            fve_configurations=fve_configurationRepository.findByNameStartingWith("small");
        else if(pocetPanelov<16)
            fve_configurations=fve_configurationRepository.findByNameStartingWith("medium");
        else
            fve_configurations=fve_configurationRepository.findByNameStartingWith("large");

        Investition investition = new Investition(fve_configurations);
        investition.setPocet_panelov(pocetPanelov);
        investition.setEffectivity(efektivita);
        investition.setVykon(pocetPanelov*0.25);



        return investition;
    }

    public Investition findInvestition(Double energy_price, Double dotacia, Household household, Investition investition){


        List<FVE_production> productionYear=productionRepository.getAllProductions();       //celorocna vyroba
        Double price=0.0;
        Double celkova_vyroba=0.0;
        Double avgConsumption= household.getCounted_overall()/365;
        int batteryNumber=(int)(avgConsumption/2);
        FVE_configurations fve_configurations = investition.getConfiguration_id();
        int pocetPanelov=investition.getPocet_panelov();


        price+=fve_configurations.getFVE_panel_price()*pocetPanelov;        //cena za panely
        price+=fve_configurations.getBattery_price()*batteryNumber;
        price+=fve_configurations.getInvertor_charger_price();
        price+=fve_configurations.getOther_price()-dotacia;

        investition.setVykon_baterie(batteryNumber);
        investition.setDotacia(dotacia);
        investition.setEnergy_price(energy_price);


        for (FVE_production item:productionYear
             ) {
            celkova_vyroba+=item.getProduction()*((household.getLocation().getSun_intensity()/1000)*investition.getVykon());      //produkcia sa udava na instalovany kWp a panel ma 0.25kWp
        }

        celkova_vyroba*=investition.getEffectivity()/100;

        investition.setNavratnost(price/(celkova_vyroba*energy_price));
        investition.setCena(price);



        return investition;

    }


    private Double getRealOverall(Example_consumption item, Appliance appliance){
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
