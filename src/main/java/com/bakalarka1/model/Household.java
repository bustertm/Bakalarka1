package com.bakalarka1.model;

/**
 * Created by Martin on 07.04.2017.
 */
import com.bakalarka1.model.consumption.Example_type;

import javax.persistence.*;


@Entity
@Table(name = "household")
public class Household{



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="overall_consumption")
    private Integer overall;

    @Column(name="residents")
    private Integer residents;

    @Column(name="household_type")
    private Integer house_type;

    @Column(name="heating_type")
    private Integer heat_type;

/*************ZISTENE SPOTREBY*************/
    @Column(name="oven_overall")
    private Double oven_overall=0.0;
    @Column(name="dishwasher_overall")
    private Double dishwasher_overall=0.0;
    @Column(name="fridge_overall")
    private Double fridge_overall=0.0;
    @Column(name="microwave_overall")
    private Double microwave_overall=0.0;
    @Column(name="washingmachine_overall")
    private Double washingmachine_overall=0.0;
    @Column(name="dryer_overall")
    private Double dryer_overall=0.0;
    @Column(name="boiler_overall")
    private Double boiler_overall=0.0;
    @Column(name="aircondition_overall")
    private Double aircondition_overall=0.0;
    @Column(name="yakuza_overall")
    private Double yakuza_overall=0.0;
    @Column(name="counted_overall")
    private Double counted_overall=0.0;



    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "example_id")        //prida sa az po zisteni idealnej vzorovej
    private Example_type example_id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


    @OneToOne(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true)
    private Appliance appliance;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOverall() {
        return overall;
    }

    public void setOverall(Integer overall) {
        this.overall = overall;
    }

    public Integer getResidents() {
        return residents;
    }

    public void setResidents(Integer residents) {
        this.residents = residents;
    }

    public Integer getHouse_type() {
        return house_type;
    }

    public void setHouse_type(Integer house_type) {
        this.house_type = house_type;
    }

    public Integer getHeat_type() {
        return heat_type;
    }

    public void setHeat_type(Integer heat_type) {
        this.heat_type = heat_type;
    }

    public Example_type getExample_id() {
        return example_id;
    }

    public void setExample_id(Example_type example_id) {
        this.example_id = example_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Appliance getAppliance() {
        return appliance;
    }

    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }


    public Double getOven_overall() {
        return oven_overall;
    }

    public void setOven_overall(Double oven_overall) {
        this.oven_overall = oven_overall;
    }

    public Double getDishwasher_overall() {
        return dishwasher_overall;
    }

    public void setDishwasher_overall(Double dishwasher_overall) {
        this.dishwasher_overall = dishwasher_overall;
    }

    public Double getFridge_overall() {
        return fridge_overall;
    }

    public void setFridge_overall(Double fridge_overall) {
        this.fridge_overall = fridge_overall;
    }

    public Double getMicrowave_overall() {
        return microwave_overall;
    }

    public void setMicrowave_overall(Double microwave_overall) {
        this.microwave_overall = microwave_overall;
    }

    public Double getWashingmachine_overall() {
        return washingmachine_overall;
    }

    public void setWashingmachine_overall(Double washingmachine_overall) {
        this.washingmachine_overall = washingmachine_overall;
    }

    public Double getDryer_overall() {
        return dryer_overall;
    }

    public void setDryer_overall(Double dryer_overall) {
        this.dryer_overall = dryer_overall;
    }

    public Double getBoiler_overall() {
        return boiler_overall;
    }

    public void setBoiler_overall(Double boiler_overall) {
        this.boiler_overall = boiler_overall;
    }

    public Double getAircondition_overall() {
        return aircondition_overall;
    }

    public void setAircondition_overall(Double aircondition_overall) {
        this.aircondition_overall = aircondition_overall;
    }

    public Double getYakuza_overall() {
        return yakuza_overall;
    }

    public void setYakuza_overall(Double yakuza_overall) {
        this.yakuza_overall = yakuza_overall;
    }

    public Double getCounted_overall() {
        return counted_overall;
    }

    public void setCounted_overall(Double counted_overall) {
        this.counted_overall = counted_overall;
    }
}
