package com.bakalarka1.service;

import com.bakalarka1.model.Appliance;
import com.bakalarka1.model.Household;
import com.bakalarka1.model.consumption.Example_type;
import com.bakalarka1.model.consumption.Monthly_consumption;

import java.util.List;

/**
 * Created by Martin on 19.04.2017.
 */
public interface AnalyseService {

    public Example_type getBestExample(Household household, Appliance appliance);
    public List<Monthly_consumption> findConsumptions(Example_type match);
}
