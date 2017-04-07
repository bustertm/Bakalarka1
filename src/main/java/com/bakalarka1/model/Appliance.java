package com.bakalarka1.model;

import javax.persistence.*;

/**
 * Created by Martin on 07.04.2017.
 */
@Entity
@Table(name= "appliance")
public class Appliance {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="oven")
    private int oven;
    @Column(name="fridge")
    private int fridge;
    @Column(name="microwave")
    private int microwave;
    @Column(name="boiler")
    private int boiler;
    @Column(name="dryer")
    private int dryer;
    @Column(name="washingmachine")
    private int washingmachine;
    @Column(name="yakuza")
    private int yakuza;
    @Column(name="aircondition")
    private int aircondition;



    @OneToOne(mappedBy = "appliance")
    private Household household;

    public Household getHousehold() {
        return household;
    }
}
