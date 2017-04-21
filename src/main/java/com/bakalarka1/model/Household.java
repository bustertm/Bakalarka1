package com.bakalarka1.model;

/**
 * Created by Martin on 07.04.2017.
 */
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



    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


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
}
