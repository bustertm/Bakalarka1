package com.bakalarka1.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martin on 07.04.2017.
 */


@Entity
@Table(name="location")
public class Location {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="nazov")
    private String nazov;

    @Column (name="sun_intensity")
    private double sun_intensity;


    @OneToMany(mappedBy = "location")
    private Set<Household> households ;

    public Set<Household> getHouseholds() {
        return this.households;
    }
}
