package com.bakalarka1.repository;


import com.bakalarka1.model.consumption.Example_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martin on 19.04.2017.
 */
@Repository("example_typeRepository")
public interface Example_typeRepository extends JpaRepository<Example_type, Integer> {


}
