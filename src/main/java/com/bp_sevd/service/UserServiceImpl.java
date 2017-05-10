package com.bp_sevd.service;

import java.util.List;

import com.bp_sevd.model.Appliance;
import com.bp_sevd.model.Household;
import com.bp_sevd.model.consumption.Monthly_consumption;
import com.bp_sevd.repository.ApplianceRepository;
import com.bp_sevd.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bp_sevd.model.Role;
import com.bp_sevd.model.User;
import com.bp_sevd.repository.RoleRepository;
import com.bp_sevd.repository.UserRepository;

/**
 * Created by Martin on 30.03.2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplianceRepository applianceRepository;
    @Autowired
    private HouseholdRepository householdRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {      //vyhladanie Usera
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {       //vytvorenie Usera
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setAnalysed(false);
        Role userRole = roleRepository.findByRole("USER");
        user.setRole(userRole);
        userRepository.save(user);


    }

    public void addHousehold(User user, Household household, Appliance appliance){      //vytvorenie domacnosti

        household.setUser(user);
        household.setAppliance(appliance);
        appliance.setHousehold(household);
        user.setHousehold(household);
        household.setAppliance(appliance);
        user.setAnalysed(true);
        appliance.setHousehold(household);
        householdRepository.save(household);
        applianceRepository.save(appliance);

    }


    public void updateHousehold(User user, Household household, Appliance appliance){       //aktualizacia domacnosti

        household.setFridge_overall(0.0);
        household.setOven_overall(0.0);
        household.setAircondition_overall(0.0);
        household.setBoiler_overall(0.0);
        household.setDishwasher_overall(0.0);
        household.setDryer_overall(0.0);
        household.setWashingmachine_overall(0.0);
        household.setYakuza_overall(0.0);
        household.setMicrowave_overall(0.0);
        household.setCounted_overall(0.0);

        householdRepository.save(household);
        applianceRepository.save(appliance);
    }





}