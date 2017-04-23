package com.bakalarka1.model.consumption;

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
}
