package com.bakalarka1.repository;

import com.bakalarka1.model.consumption.Example_consumption;
import com.bakalarka1.model.consumption.Example_type;
import com.bakalarka1.model.consumption.Finded_example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Martin on 21.04.2017.
 */
@Repository("example_consumptionRepository")
public interface Example_consumptionRepository extends JpaRepository<Example_consumption, Integer>{


 /*   @Query("select u from Example_consuption u where u.household_id = ?1")
    List<Example_consumption> findByType(Example_type type);*/

    List<Example_consumption> findByType(Example_type type);

  /*  @Query(value = "SELECT ec FROM example_consumption ec WHERE ec.household_id = ?1", nativeQuery = true)
    List<Example_consumption> findByHousehold(Integer household_id);

       /*       @Query(value="select e.household_id, sum(e.overall) as found from example_consumption e " +
            "where e.household_id in :ids "+
            "group by e.household_id"
                      , nativeQuery = true)
    List<Finded_example> getOveralls(@Param("ids") List<Integer> idecka);*/


}
