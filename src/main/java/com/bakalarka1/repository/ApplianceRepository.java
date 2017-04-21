package com.bakalarka1.repository;

/**
 * Created by Martin on 08.04.2017.
 */

import com.bakalarka1.model.Appliance;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("applianceRepository")
public interface ApplianceRepository extends JpaRepository<Appliance, Integer>{
}
