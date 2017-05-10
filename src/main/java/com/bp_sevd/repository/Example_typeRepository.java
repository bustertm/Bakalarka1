package com.bp_sevd.repository;


import com.bp_sevd.model.consumption.Example_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Martin on 19.04.2017.
 */
@Repository("example_typeRepository")
public interface Example_typeRepository extends JpaRepository<Example_type, Integer> {


}
