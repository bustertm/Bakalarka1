package com.bp_sevd.model.production;

import javax.persistence.*;

/**
 * Created by Martin on 26.04.2017.
 */
@Entity
@Table(name = "configurations")
public class FVE_configurations {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name= "name")
    private String name;

    @Column(name= "FVE_panel_type")
    private String FVE_panel_type;

    @Column(name= "FVE_panel_power")
    private Integer FVE_panel_power;


    @Column(name= "invertor_charger_type")
    private String invertor_charger_type;

    @Column(name= "battery_type")
    private String battery_type;

    @Column(name= "battery_price")
    private Double battery_price;

    @Column(name= "FVE_panel_price")
    private Double FVE_panel_price;


    @Column(name= "invertor_charger_price")
    private Double invertor_charger_price;

    @Column(name= "other_price")
    private Double other_price;


    public int getId() {
        return id;
    }

    public Double getBattery_price() {
        return battery_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBattery_price(Double battery_price) {
        this.battery_price = battery_price;
    }

    public Integer getFVE_panel_power() {
        return FVE_panel_power;
    }

    public void setFVE_panel_power(Integer FVE_panel_power) {
        this.FVE_panel_power = FVE_panel_power;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFVE_panel_type() {
        return FVE_panel_type;
    }

    public void setFVE_panel_type(String FVE_panel_type) {
        this.FVE_panel_type = FVE_panel_type;
    }


    public String getInvertor_charger_type() {
        return invertor_charger_type;
    }

    public void setInvertor_charger_type(String invertor_charger_type) {
        this.invertor_charger_type = invertor_charger_type;
    }

    public Double getInvertor_charger_price() {
        return invertor_charger_price;
    }

    public void setInvertor_charger_price(Double invertor_charger_price) {
        this.invertor_charger_price = invertor_charger_price;
    }

    public String getBattery_type() {
        return battery_type;
    }

    public void setBattery_type(String battery_type) {
        this.battery_type = battery_type;
    }

    public Double getFVE_panel_price() {
        return FVE_panel_price;
    }

    public void setFVE_panel_price(Double FVE_panel_price) {
        this.FVE_panel_price = FVE_panel_price;
    }




    public Double getOther_price() {
        return other_price;
    }

    public void setOther_price(Double other_price) {
        this.other_price = other_price;
    }
}
