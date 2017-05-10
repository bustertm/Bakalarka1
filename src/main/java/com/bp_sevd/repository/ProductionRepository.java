package com.bp_sevd.repository;

import com.bp_sevd.model.production.FVE_production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martin on 26.04.2017.
 */
@Repository("productionRepository")
public interface ProductionRepository extends JpaRepository<FVE_production, Integer>{


    @Query(value="select new com.bp_sevd.model.production.FVE_production (p.id AS id, p.date AS date,\n" +
            "AVG(p.production) AS production, p.elektraren_id as elektraren_id)\n" +
            "FROM FVE_production as p\n" +
            "WHERE elektraren_id IN (1,2,3) and (date>='2015-03-19' and date<'2015-10-07')\n" +
            "GROUP BY date ORDER BY date asc")
    List<FVE_production> getKeyProductions();          //vyroba v klucovych mesiacoch

    @Query(value="select new com.bp_sevd.model.production.FVE_production (p.id AS id, p.date AS date,\n" +
            "AVG(p.production) AS production, p.elektraren_id as elektraren_id)\n" +
            "FROM FVE_production as p\n" +
            "WHERE elektraren_id IN (1,2,3) and (date>='2015-01-01' and date<'2016-01-01')\n" +
            "GROUP BY date ORDER BY date asc")
    List<FVE_production> getAllProductions();       //celorocna vyroba




}
