package com.bakalarka1.model.production;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by Martin on 25.04.2017.
 */
@Entity
@Table(name = "production")
public class FVE_production {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "datum")
    private java.util.Date date;

    @Column(name = "production")
    private double production;

    @Column(name = "elektraren_id")
    private int elektraren_id;


    public FVE_production(int id, java.util.Date date, double production, int elektraren_id) {
        this.id = id;
        this.date = date;
        this.production = production;
        this.elektraren_id = elektraren_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public double getProduction() {
        return production;
    }

    public void setProduction(double production) {
        this.production = production;
    }

    public int getElektraren_id() {
        return elektraren_id;
    }

    public void setElektraren_id(int elektraren_id) {
        this.elektraren_id = elektraren_id;
    }
}
