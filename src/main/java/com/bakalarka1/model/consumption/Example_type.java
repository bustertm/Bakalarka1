package com.bakalarka1.model.consumption;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 03.04.2017.
 */
@Entity
@Table(name = "example_type")
public class Example_type {

    @Id
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "type")
    private List<Example_consumption> example_consumptions;

    @Column(name="oven")
    private Integer oven;
    @Column(name="fridge")
    private Integer fridge;
    @Column(name="dishwasher")
    private Integer dishwasher;
    @Column(name="microwave")
    private Integer microwave;
    @Column(name="boiler")
    private Integer boiler;
    @Column(name="dryer")
    private Integer dryer;
    @Column(name="washingmachine")
    private Integer washingmachine;
    @Column(name="yakuza")
    private Integer yakuza;
    @Column(name="aircondition")
    private Integer aircondition;
    @Column(name="overall_consumption")
    private Double overall;



    public Double getOverall() {
        return overall;
    }

    public void setOverall(Double overall) {
        this.overall = overall;
    }



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

    public Integer getFridge() {
        return fridge;
    }

    public void setFridge(Integer fridge) {
        this.fridge = fridge;
    }

    public Integer getDishwasher() {
        return dishwasher;
    }

    public void setDishwasher(Integer dishwasher) {
        this.dishwasher = dishwasher;
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
}
