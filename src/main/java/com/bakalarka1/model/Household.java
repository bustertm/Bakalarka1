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
    private int overall;

    @Column(name="residents")
    private int residents;

    @Column(name="household_type")
    private int house_t;

    @Column(name="heating_type")
    private int heat_t;



    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appliance_id")
    private Appliance appliance;

}
