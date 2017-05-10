package com.bp_sevd.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import org.springframework.data.annotation.Transient;

/**
 * Created by Martin on 30.03.2017.
 */
@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    @Email(message = "*Prosím skontroluj si email")
    @NotEmpty(message = "*Zadaj email")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Heslo musí obsahovať aspoň 5 znakov")
    @NotEmpty(message = "*Zadaj heslo")
    @Transient
    private String password;
    @Column(name = "name")
    @NotEmpty(message = "*Zadaj meno")
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "*Zadaj priezvisko")
    private String lastName;
    @Column(name="analysed")
    private Boolean analysed;
    @Column(name = "active")
    private int active;

    @ManyToOne
    @JoinColumn(name = "role_id")       //rola pouzivatela
    private Role role;

    public Role getRole() {
        return role;
    }

    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private Household household;


    public Household getHousehold() {
        return household;
    }

    public void setRole(Role role){
        this.role=role;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    public Boolean getAnalysed() {
        return analysed;
    }

    public void setAnalysed(Boolean analysed) {
        this.analysed = analysed;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }
}
