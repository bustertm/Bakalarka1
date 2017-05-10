package com.bp_sevd.repository;

import com.bp_sevd.model.consumption.Example_consumption;
import com.bp_sevd.model.consumption.Example_type;
import com.bp_sevd.model.consumption.Monthly_consumption;
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


    List<Example_consumption> findByTypeAndDateBetweenOrderByDateAsc(Example_type type,Date start,Date end );

    @Query(value="SELECT new com.bp_sevd.model.consumption.Monthly_consumption(e.type AS household_id, EXTRACT(MONTH from e.date) AS months, \n" +
            "SUM(e.oven)as oven, SUM(e.dishwasher)as dishwasher, SUM(e.fridge)as fridge, SUM(e.microwave)as microwave,\n" +
            "SUM(e.boiler)as boiler, SUM(e.dryer)as dryer,SUM(e.washingmachine)as washingmachine, SUM(e.yakuza)as yakuza,\n" +
            "SUM(e.aircondition)as aircondition, SUM(e.overall)as overall)\n" +
            "FROM Example_consumption as e\n" +
            "WHERE household_id = :ids\n" +
            "GROUP BY household_id,EXTRACT(MONTH from e.date)")
    List<Monthly_consumption> findMonthConsumption(@Param("ids") Example_type type);


}
