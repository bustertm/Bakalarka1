package com.bp_sevd.repository;

/**
 * Created by Martin on 08.04.2017.
 */

import com.bp_sevd.model.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("applianceRepository")
public interface ApplianceRepository extends JpaRepository<Appliance, Integer>{
}
