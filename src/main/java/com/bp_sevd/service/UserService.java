package com.bp_sevd.service;

import com.bp_sevd.model.Appliance;
import com.bp_sevd.model.Household;
import com.bp_sevd.model.User;
import com.bp_sevd.model.consumption.Monthly_consumption;

import java.util.List;

/**
 * Created by Martin on 30.03.2017.
 */
public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);


    public void addHousehold(User user, Household household, Appliance appliance);
    public void updateHousehold(User user, Household household, Appliance appliance);

}