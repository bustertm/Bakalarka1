package com.bp_sevd.service;

import com.bp_sevd.model.Appliance;
import com.bp_sevd.model.Household;
import com.bp_sevd.model.consumption.Example_type;
import com.bp_sevd.model.consumption.Monthly_consumption;

import java.util.List;

/**
 * Created by Martin on 19.04.2017.
 */

public interface AnalyseService {

    public Example_type getBestExample(Household household, Appliance appliance);
    public List<Monthly_consumption> findConsumptions(Example_type match,Appliance appliance, Household household);

}
