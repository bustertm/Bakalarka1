package com.bakalarka1.service;

import java.util.Arrays;
import java.util.HashSet;

import com.bakalarka1.model.Appliance;
import com.bakalarka1.model.Household;
import com.bakalarka1.repository.ApplianceRepository;
import com.bakalarka1.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bakalarka1.model.Role;
import com.bakalarka1.model.User;
import com.bakalarka1.repository.RoleRepository;
import com.bakalarka1.repository.UserRepository;

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
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setAnalysed(false);
        Role userRole = roleRepository.findByRole("USER");
        user.setRole(userRole);
        userRepository.save(user);


    }

    public void addHousehold(User user, Household household, Appliance appliance){

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

    public void updateHousehold(User user, Household household, Appliance appliance){

        householdRepository.save(household);
        applianceRepository.save(appliance);
    }



}