package com.bp_sevd.repository;

import com.bp_sevd.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martin on 25.04.2017.
 */
@Repository("locationRepository")
public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findByOrderBySunIntensity();
}
