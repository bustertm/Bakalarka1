package com.bakalarka1.repository;

import com.bakalarka1.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Martin on 08.04.2017.
 */
@Repository("householdRepository")
public interface HouseholdRepository extends JpaRepository<Household, Integer>{

}
