package com.bakalarka1.model.consumption;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by Martin on 03.04.2017.
 */
public class example_type {

    @Id
    @Column(name = "household_id")
    private int id;

    @Column(name="oven")
    private java.sql.Blob oven;
    @Column(name="fridge")
    private java.sql.Blob fridge;
    @Column(name="microwave")
    private java.sql.Blob microwave;
    @Column(name="boiler")
    private java.sql.Blob boiler;
    @Column(name="dryer")
    private java.sql.Blob dryer;
    @Column(name="washingmachine")
    private java.sql.Blob washingmachine;
    @Column(name="yakuza")
    private java.sql.Blob yakuza;
    @Column(name="aircondition")
    private java.sql.Blob aircondition;

}
