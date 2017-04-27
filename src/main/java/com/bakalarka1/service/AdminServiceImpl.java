package com.bakalarka1.service;

import com.bakalarka1.model.production.FVE_configurations;
import com.bakalarka1.repository.FVE_configurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
