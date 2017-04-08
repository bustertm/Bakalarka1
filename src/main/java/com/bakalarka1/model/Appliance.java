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

    @Column(name = "oven")
    private Integer oven;
    @Column(name = "dishwasher")
    private Integer dishwasher;
    @Column(name = "fridge")
    private Integer fridge;
    @Column(name = "microwave")
    private Integer microwave;
    @Column(name = "boiler")
    private Integer boiler;
    @Column(name = "dryer")
    private Integer dryer;
    @Column(name = "washingmachine")
    private Integer washingmachine;
    @Column(name = "yakuza")
    private Integer yakuza;
    @Column(name = "aircondition")
    private Integer aircondition;


    @OneToOne(mappedBy = "appliance")
    private Household household;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOven() {
        return oven;
    }

    public void setOven(Integer oven) {
        this.oven = oven;
    }

    public Integer getDishwasher() {
        return dishwasher;
    }

    public void setDishwasher(Integer dishwasher) {
        this.dishwasher = dishwasher;
    }

    public Integer getFridge() {
        return fridge;
    }

    public void setFridge(Integer fridge) {
        this.fridge = fridge;
    }

    public Integer getMicrowave() {
        return microwave;
    }

    public void setMicrowave(Integer microwave) {
        this.microwave = microwave;
    }

    public Integer getBoiler() {
        return boiler;
    }

    public void setBoiler(Integer boiler) {
        this.boiler = boiler;
    }

    public Integer getDryer() {
        return dryer;
    }

    public void setDryer(Integer dryer) {
        this.dryer = dryer;
    }

    public Integer getWashingmachine() {
        return washingmachine;
    }

    public void setWashingmachine(Integer washingmachine) {
        this.washingmachine = washingmachine;
    }

    public Integer getYakuza() {
        return yakuza;
    }

    public void setYakuza(Integer yakuza) {
        this.yakuza = yakuza;
    }

    public Integer getAircondition() {
        return aircondition;
    }

    public void setAircondition(Integer aircondition) {
        this.aircondition = aircondition;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }
}