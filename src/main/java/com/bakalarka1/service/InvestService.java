package com.bakalarka1.service;

import com.bakalarka1.model.Household;
import com.bakalarka1.model.Location;
import com.bakalarka1.model.production.Investition;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Martin on 24.04.2017.
 */
public interface InvestService {

    public List<Location> getLocations();
    public Location findByID(int id);
    public void addLocation(Household household, Location location);
    public Investition findBestProduction(Household household) throws ParseException;
    public Investition findInvestition(Investition investition, Household household);
}

