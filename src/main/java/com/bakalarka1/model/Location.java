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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public double getSun_intensity() {
        return sun_intensity;
    }

    public void setSun_intensity(double sun_intensity) {
        this.sun_intensity = sun_intensity;
    }

    public void setHouseholds(Set<Household> households) {
        this.households = households;
    }
}
