package com.bakalarka1.service;

import com.bakalarka1.model.Appliance;
import com.bakalarka1.model.Household;
import com.bakalarka1.model.consumption.Example_consumption;
import com.bakalarka1.model.consumption.Example_type;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 19.04.2017.
 */
public interface AnalyseService {

    public List<Example_type> getBestExample(Household household, Appliance appliance);
    public void findConsumptions(List<Example_type> matches);
}
