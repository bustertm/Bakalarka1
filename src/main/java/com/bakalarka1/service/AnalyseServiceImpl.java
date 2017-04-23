package com.bakalarka1.service;

import com.bakalarka1.model.Appliance;
import com.bakalarka1.model.Household;
import com.bakalarka1.model.consumption.Example_type;
import com.bakalarka1.model.consumption.Monthly_consumption;
import com.bakalarka1.repository.ApplianceRepository;
import com.bakalarka1.repository.Example_consumptionRepository;
import com.bakalarka1.repository.Example_typeRepository;
import com.bakalarka1.repository.HouseholdRepository;
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
    private ApplianceRepository applianceRepository;
    @Autowired
    private Example_typeRepository example_typeRepository;
    @Autowired
    private HouseholdRepository householdRepository;
    @Autowired
    private Example_consumptionRepository example_consumptionRepository;





    public List<Example_type> getBestExample(Household household, Appliance appliance){


        int mismatch=0;
        double difference=9999999;
        double rozdiel;
        Example_type bestDiffID=null;

        List<Example_type> matches = new ArrayList<>();

        List<Object[]> finded_examples = new ArrayList<>();
        List<Example_type> finalIDs= new ArrayList<>();


        for (Example_type example_type:example_typeRepository.findAll()) {
            if(appliance.getOven()>0 && example_type.getOven()==0)
            {
                mismatch++;
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
                matches.add(example_type);
                rozdiel=Math.abs(household.getOverall()-example_type.getOverall());

                if(household.getOverall()>0 && difference>rozdiel){
                    difference=rozdiel;
                    bestDiffID=example_type;

                }
            }

            mismatch=0;


        }


        if(bestDiffID!=null){
            finalIDs.add(0,bestDiffID);
            return finalIDs;
        }

        return matches;

    }

    public List<Monthly_consumption> findConsumptions(List<Example_type> matches){

       // List<Monthly_consumption> ex = new ArrayList<>();

       // ex=example_consumptionRepository.findByType(matches.get(0));
        return example_consumptionRepository.findMonthConsumption(matches.get(0));




    }
}
