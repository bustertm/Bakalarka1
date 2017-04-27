package com.bakalarka1.model.consumption;



import javax.persistence.*;
import java.sql.Date;


/**
 * Created by Martin on 03.04.2017.
 */


@Entity
@Table(name = "example_consumption")
public class Example_consumption {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "household_id")
    private Example_type type;

    @Column(name = "date")
    private java.sql.Date date;


    @Column(name="oven")
    private double oven;
    @Column(name="dishwasher")
    private double dishwasher;
    @Column(name="fridge")
    private double fridge;
    @Column(name="microwave")
    private double microwave;
    @Column(name="boiler")
    private double boiler;
    @Column(name="dryer")
    private double dryer;
    @Column(name="washingmachine")
    private double washingmachine;
    @Column(name="yakuza")
    private double yakuza;
    @Column(name="aircondition")
    private double aircondition;
    @Column(name="overall")
    private double overall;



    public Example_type getType() {
        return type;
    }

    public void setType(Example_type Example_type) {
        this.type = Example_type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOven(double oven) {
        this.oven = oven;
    }

    public void setFridge(double fridge) {
        this.fridge = fridge;
    }

    public void setMicrowave(double microwave) {
        this.microwave = microwave;
    }

    public void setBoiler(double boiler) {
        this.boiler = boiler;
    }

    public void setDryer(double dryer) {
        this.dryer = dryer;
    }

    public void setWashingmachine(double washingmachine) {
        this.washingmachine = washingmachine;
    }

    public void setYakuza(double yakuza) {
        this.yakuza = yakuza;
    }

    public void setAircondition(double aircondition) {
        this.aircondition = aircondition;
    }

    public void setOverall(double overall) {
        this.overall = overall;
    }

    public Date getDate() {
        return date;
    }

    public double getOven() {
        return oven;
    }

    public double getFridge() {
        return fridge;
    }

    public double getMicrowave() {
        return microwave;
    }

    public double getBoiler() {
        return boiler;
    }

    public double getDryer() {
        return dryer;
    }

    public double getWashingmachine() {
        return washingmachine;
    }

    public double getYakuza() {
        return yakuza;
    }

    public double getAircondition() {
        return aircondition;
    }

    public double getOverall() {
        return overall;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDishwasher() {
        return dishwasher;
    }

    public void setDishwasher(double dishwasher) {
        this.dishwasher = dishwasher;
    }
}
