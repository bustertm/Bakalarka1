package com.bp_sevd.service;

import com.bp_sevd.model.Household;
import com.bp_sevd.model.Location;
import com.bp_sevd.model.production.Investment;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Martin on 24.04.2017.
 */
public interface InvestService {

    public List<Location> getLocations();
    public Location findByID(int id);
    public void addLocation(Household household, Location location);
    public Investment findBestProduction(Household household) throws ParseException;
    public Investment findInvestment(Double energy_price, Double dotacia, Household household, Investment investment);
}

