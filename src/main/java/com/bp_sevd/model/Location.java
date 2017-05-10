package com.bp_sevd.model;

import javax.persistence.*;
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

    @Column (name="sunIntensity")
    private double sunIntensity;


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

    public double getSunIntensity() {
        return sunIntensity;
    }

    public void setSunIntensity(double sunIntensity) {
        this.sunIntensity = sunIntensity;
    }

    public void setHouseholds(Set<Household> households) {
        this.households = households;
    }
}
