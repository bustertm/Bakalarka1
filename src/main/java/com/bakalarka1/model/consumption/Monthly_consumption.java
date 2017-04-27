package com.bakalarka1.model.consumption;

import java.sql.Date;

/**
 * Created by Martin on 21.04.2017.
 */





public class Monthly_consumption {




    private Example_type household_id;
    private Integer months;


    private Double oven;
    private Double dishwasher;
    private Double fridge;
    private Double microwave;
    private Double boiler;
    private Double dryer;
    private Double washingmachine;

    private Double yakuza;

    private Double aircondition;

    private Double overall;

   // Monthly_consumption(){}



    public Monthly_consumption(Example_type household_id, Integer months, Double oven, Double dishwasher, Double fridge, Double microwave, Double boiler, Double dryer, Double washingmachine, Double yakuza, Double aircondition, Double overall) {
        this.household_id = household_id;
        this.months = months;

        this.oven = oven;
        this.dishwasher = dishwasher;
        this.fridge = fridge;
        this.microwave = microwave;
        this.boiler = boiler;
        this.dryer = dryer;
        this.washingmachine = washingmachine;
        this.yakuza = yakuza;
        this.aircondition = aircondition;
        this.overall = overall;
    }



    public Example_type getHousehold_id() {
        return household_id;
    }

    public void setHousehold_id(Example_type household_id) {
        this.household_id = household_id;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Double getOven() {
        return oven;
    }

    public void setOven(Double oven) {
        this.oven = oven;
    }

    public Double getDishwasher() {
        return dishwasher;
    }

    public void setDishwasher(Double dishwasher) {
        this.dishwasher = dishwasher;
    }

    public Double getFridge() {
        return fridge;
    }

    public void setFridge(Double fridge) {
        this.fridge = fridge;
    }

    public Double getMicrowave() {
        return microwave;
    }

    public void setMicrowave(Double microwave) {
        this.microwave = microwave;
    }

    public Double getBoiler() {
        return boiler;
    }

    public void setBoiler(Double boiler) {
        this.boiler = boiler;
    }

    public Double getDryer() {
        return dryer;
    }

    public void setDryer(Double dryer) {
        this.dryer = dryer;
    }

    public Double getWashingmachine() {
        return washingmachine;
    }

    public void setWashingmachine(Double washingmachine) {
        this.washingmachine = washingmachine;
    }

    public Double getYakuza() {
        return yakuza;
    }

    public void setYakuza(Double yakuza) {
        this.yakuza = yakuza;
    }

    public Double getAircondition() {
        return aircondition;
    }

    public void setAircondition(Double aircondition) {
        this.aircondition = aircondition;
    }

    public Double getOverall() {
        return overall;
    }

    public void setOverall(Double overall) {
        this.overall = overall;
    }
}
