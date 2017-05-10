package com.bp_sevd.service;

import com.bp_sevd.model.Appliance;
import com.bp_sevd.model.Household;
import com.bp_sevd.model.consumption.Example_type;
import com.bp_sevd.model.consumption.Monthly_consumption;
import com.bp_sevd.repository.ApplianceRepository;
import com.bp_sevd.repository.Example_consumptionRepository;
import com.bp_sevd.repository.Example_typeRepository;
import com.bp_sevd.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 19.04.2017.
 */
@Service("analyseService")
public class AnalyseServiceImpl implements AnalyseService{




    @Autowired
    private Example_typeRepository example_typeRepository;
    @Autowired
    private HouseholdRepository householdRepository;
    @Autowired
    private Example_consumptionRepository example_consumptionRepository;





    public Example_type getBestExample(Household household, Appliance appliance){


        int mismatch=0, bestMismatch=10;
        double difference=9999999;
        double rozdiel;
        Example_type bestDiffID=null;
        Example_type bestMatchID=null;


        List<Object[]> finded_examples = new ArrayList<>();
        List<Example_type> finalIDs= new ArrayList<>();


        for (Example_type example_type:example_typeRepository.findAll()) {
            if(appliance.getOven()>0 && example_type.getOven()==0)          //porovnam ci zadana domacnost ma sporak a ci ho ma aj vzorova
            {
                mismatch++;                                                 //ak sa vzorova nema sporak ale zadana ho ma beriem to ako nezhodu
            }

            if(appliance.getFridge()>0 && example_type.getFridge()==0)

            {
                mismatch++;
            }

            if(appliance.getDishwasher()>0 && example_type.getDishwasher()==0)

            {
                mismatch++;
            }

            if(appliance.getMicrowave()>0 && example_type.getMicrowave()==0)
            {
                mismatch++;
            }

            if(appliance.getWashingmachine()>0 && example_type.getWashingmachine()==0)
            {
                mismatch++;
            }

            if(appliance.getDryer()>0 && example_type.getDryer()==0)
            {
                mismatch++;
            }

            if(appliance.getBoiler()>0 && example_type.getBoiler()==0)
            {
                mismatch++;
            }

            if(appliance.getAircondition()>0 && example_type.getAircondition()==0)
            {
                mismatch++;
            }

            if(appliance.getYakuza()>0 && example_type.getYakuza()==0)
            {
                mismatch++;
            }

            if(mismatch==0) {

                rozdiel=Math.abs(household.getOverall()-example_type.getOverall());

                if(household.getOverall()>0 && difference>rozdiel){
                    difference=rozdiel;
                    bestDiffID=example_type;
                }
            }

            else if(mismatch<bestMismatch){
                    bestMatchID=example_type;       //beriem aspon trochu podobny ak neni presna zhoda
                    bestMismatch=mismatch;
            }

            mismatch=0;


        }


        if(bestDiffID!=null){
            household.setExample_id(bestDiffID);
            householdRepository.save(household);
            return bestDiffID;                      //vraciam najlepsiu zhodu
        }

        household.setExample_id(bestMatchID);
        householdRepository.save(household);
        return bestMatchID;                  //vratim aspon najpodobnejsiu zhodu ak ani jedna vzorova nemala mismatch==0

    }

    public List<Monthly_consumption> findConsumptions(Example_type match, Appliance appliance, Household household){

        List<Monthly_consumption> monthly_con_list = new ArrayList<>();

        monthly_con_list=example_consumptionRepository.findMonthConsumption(match);     //vytiahnem spotreby agregovane na mesiace pre vzorovu domacnost
        household.setCounted_overall(0.0);

        for (Monthly_consumption item:monthly_con_list)         //uprava spotreby podla pocetnosti spotrebicov, v databaze je jednotkovy vzor
        {
            setCustomOverall(item,household,appliance);
        }

        householdRepository.save(household);
        return monthly_con_list;

    }


    private void setCustomOverall(Monthly_consumption item, Household household, Appliance appliance){

        if(appliance.getOven()>0){
            item.setOverall(item.getOverall()-item.getOven());     //odcitam z celkovej spotreby
            item.setOven(appliance.getOven()*item.getOven());           //prenasobim spotrebu pocetnostou spotrebica
            item.setOverall(item.getOverall()+item.getOven());     //pricitam naspat do celkovej prenasobenu spotrebu

            household.setOven_overall(household.getOven_overall()+item.getOven());      //do databazy ukladam celkovu za rok
        }
        else item.setOverall(item.getOverall()-item.getOven());         //odcitam spotrebic z celkovej kedze ho nemame

        if(appliance.getFridge()>0){
            item.setOverall(item.getOverall()-item.getFridge());
            item.setFridge(appliance.getFridge()*item.getFridge());
            item.setOverall(item.getOverall()+item.getFridge());

            household.setFridge_overall(household.getFridge_overall()+item.getFridge());
        }
        else item.setOverall(item.getOverall()-item.getFridge());

        if(appliance.getDishwasher()>0){
            item.setOverall(item.getOverall()-item.getDishwasher());
            item.setDishwasher(appliance.getDishwasher()*item.getDishwasher());
            item.setOverall(item.getOverall()+item.getDishwasher());

            household.setDishwasher_overall(household.getDishwasher_overall()+item.getDishwasher());
        }
        else item.setOverall(item.getOverall()-item.getDishwasher());

        if(appliance.getMicrowave()>0){
            item.setOverall(item.getOverall()-item.getMicrowave());
            item.setMicrowave(appliance.getMicrowave()*item.getMicrowave());
            item.setOverall(item.getOverall()+item.getMicrowave());

            household.setMicrowave_overall(household.getMicrowave_overall()+item.getMicrowave());
        }
        else item.setOverall(item.getOverall()-item.getMicrowave());

        if(appliance.getWashingmachine()>0){
            item.setOverall(item.getOverall()-item.getWashingmachine());
            item.setWashingmachine(appliance.getWashingmachine()*item.getWashingmachine());
            item.setOverall(item.getOverall()+item.getWashingmachine());

            household.setWashingmachine_overall(household.getWashingmachine_overall()+item.getWashingmachine());
        }
        else item.setOverall(item.getOverall()-item.getWashingmachine());

        if(appliance.getDryer()>0){
            item.setOverall(item.getOverall()-item.getDryer());
            item.setDryer(appliance.getDryer()*item.getDryer());
            item.setOverall(item.getOverall()+item.getDryer());

            household.setDryer_overall(household.getDryer_overall()+item.getDryer());
        }
        else item.setOverall(item.getOverall()-item.getDryer());

        if(appliance.getBoiler()>0){
            item.setOverall(item.getOverall()-item.getBoiler());
            item.setBoiler(appliance.getBoiler()*item.getBoiler());
            item.setOverall(item.getOverall()+item.getBoiler());

            household.setBoiler_overall(household.getBoiler_overall()+item.getBoiler());
        }
        else item.setOverall(item.getOverall()-item.getBoiler());

        if(appliance.getAircondition()>0){
            item.setOverall(item.getOverall()-item.getAircondition());
            item.setAircondition(appliance.getAircondition()*item.getAircondition());
            item.setOverall(item.getOverall()+item.getAircondition());

            household.setAircondition_overall(household.getAircondition_overall()+item.getAircondition());
        }
        else item.setOverall(item.getOverall()-item.getAircondition());

        if(appliance.getYakuza()>0){
            item.setOverall(item.getOverall()-item.getYakuza());
            item.setYakuza(appliance.getYakuza()*item.getYakuza());
            item.setOverall(item.getOverall()+item.getYakuza());

            household.setYakuza_overall(household.getYakuza_overall()+item.getYakuza());
        }
        else item.setOverall(item.getOverall()-item.getYakuza());

        household.setCounted_overall(household.getCounted_overall()+item.getOverall());     //celkova spotreba za rok z mesacnych


    }
}
