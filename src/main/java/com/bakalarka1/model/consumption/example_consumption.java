package com.bakalarka1.model.consumption;



import javax.persistence.*;
import java.sql.Date;


/**
 * Created by Martin on 03.04.2017.
 */


@Entity
@Table(name = "example_consumption")
public class example_consumption {



    @Id
    @Column(name = "household_id")
    private int id;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name="oven")
    private double oven;
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

    public int getId() {
        return id;
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
}
