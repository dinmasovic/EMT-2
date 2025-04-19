package com.example.demo.model.domain;

import com.example.demo.model.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "app_users")
public class User implements UserDetails {

    @Id
    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private String surname;

    @OneToMany
    private List<Place> rented;

    @ManyToMany
    private List<Place> planningToRent;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;



    public User() {
        rented = new ArrayList<>();
    }

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        rented = new ArrayList<>();
        planningToRent=new ArrayList<>();
    }

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = Role.ROLE_USER;
        rented = new ArrayList<>();
        planningToRent=new ArrayList<>();
    }

    public User(UserDetails userDetails) {
        this.username = userDetails.getUsername();
        this.password = userDetails.getPassword();
        rented = new ArrayList<>();
        planningToRent=new ArrayList<>();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Place> getRented() {
        return rented;
    }

    public Role getRole() {
        return role;
    }
    public void addRented(Place place){
        rented.add(place);
    }
    public void addPlanning(Place place){ planningToRent.add(place);}
    public List<Place> getPlanning(){
        return planningToRent;
    }
}
