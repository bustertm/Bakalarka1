package com.bakalarka1.repository;

import com.bakalarka1.model.consumption.Example_consumption;
import com.bakalarka1.model.consumption.Example_type;
import com.bakalarka1.model.consumption.Monthly_consumption;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


/**
 * Created by Martin on 21.04.2017.
 */
@Repository("example_consumptionRepository")
public interface Example_consumptionRepository extends JpaRepository<Example_consumption, Integer>{


  /*  @Query(value="select * from example_consumption u where u.household_id = ?1", nativeQuery = true)
    List<Example_consumption> findByType(Example_type type);*/


  /*  @Query(value="SELECT e\n" +
            "FROM Example_consumption e\n" +
            "WHERE e.household_id = :ids AND e.date >= :startD AND e.date< :endD"
            )*/
    List<Example_consumption> findByTypeAndDateBetweenOrderByDateAsc(Example_type type,Date start,Date end );
  //List<Example_consumption> findByDateBetween(Date start, Date end);

  /*  @Query(value="SELECT new com.bakalarka1.model.consumption.Monthly_consumption(e.type AS household_id,nullif(1,1) month, e.date AS date,\n" +
            "e.oven as oven, e.dishwasher as dishwasher, e.fridge as fridge,  e.microwave as microwave,\n" +
            " e.boiler as boiler,  e.dryer as dryer, e.washingmachine as washingmachine,  e.yakuza as yakuza,\n" +
            " e.aircondition as aircondition,  e.overall as overall)\n" +
            "FROM Example_consumption as e\n" +
            "WHERE household_id = :ids\n"
            )
    List<Monthly_consumption> findDayConsumption(@Param("ids") Example_type type);*/


    @Query(value="SELECT new com.bakalarka1.model.consumption.Monthly_consumption(e.type AS household_id, EXTRACT(MONTH from e.date) AS months, \n" +
            "SUM(e.oven)as oven, SUM(e.dishwasher)as dishwasher, SUM(e.fridge)as fridge, SUM(e.microwave)as microwave,\n" +
            "SUM(e.boiler)as boiler, SUM(e.dryer)as dryer,SUM(e.washingmachine)as washingmachine, SUM(e.yakuza)as yakuza,\n" +
            "SUM(e.aircondition)as aircondition, SUM(e.overall)as overall)\n" +
            "FROM Example_consumption as e\n" +
            "WHERE household_id = :ids\n" +
            "GROUP BY household_id,EXTRACT(MONTH from e.date)")
    List<Monthly_consumption> findMonthConsumption(@Param("ids") Example_type type);





  /*  @Query(value = "SELECT ec FROM example_consumption ec WHERE ec.household_id = ?1", nativeQuery = true)
    List<Example_consumption> findByHousehold(Integer household_id);

   /*           @Query(value="select e.household_id, sum(e.overall) as found from example_consumption e " +
            "where e.household_id in :ids "+
            "group by e.household_id"
                      , nativeQuery = true)
    List<Monthly_consumption> getOveralls(@Param("ids") List<Integer> idecka);*/


}
