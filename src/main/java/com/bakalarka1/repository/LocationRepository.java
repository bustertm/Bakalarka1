package com.bakalarka1.repository;

import com.bakalarka1.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Martin on 25.04.2017.
 */
@Repository("locationRepository")
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
