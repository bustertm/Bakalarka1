package com.bp_sevd.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Martin on 30.03.2017.
 */
@Entity
@Table(name = "role")
public class Role {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="role")
    private String role;


    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


}
