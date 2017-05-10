package com.bp_sevd.service;

import com.bp_sevd.model.production.FVE_configurations;
import com.bp_sevd.repository.FVE_configurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Martin on 26.04.2017.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    private FVE_configurationRepository fve_configurationRepository;


    @Override
    public void add_fve_configuration(FVE_configurations fve_configurations) {
        fve_configurationRepository.save(fve_configurations);
    }
}
