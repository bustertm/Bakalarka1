package com.bp_sevd.repository;

import com.bp_sevd.model.production.FVE_configurations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * Created by Martin on 26.04.2017.
 */
@Repository("fve_configurationRepository")
public interface FVE_configurationRepository extends JpaRepository<FVE_configurations, Integer>{

    FVE_configurations findByNameStartingWith(String name);
}
