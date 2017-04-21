package com.bakalarka1.service;

import com.bakalarka1.model.Appliance;
import com.bakalarka1.model.Household;
import com.bakalarka1.model.User;

/**
 * Created by Martin on 30.03.2017.
 */
public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);


    public void addHousehold(User user, Household household, Appliance appliance);
    public void updateHousehold(User user, Household household, Appliance appliance);
}