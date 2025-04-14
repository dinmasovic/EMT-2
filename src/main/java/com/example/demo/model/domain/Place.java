package com.example.demo.model.domain;

import com.example.demo.model.enumerations.Category;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Place {
    //id (Long), name (String), category (enum), host (Host), numRooms (Integer).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Host host;
    int numRooms;
    boolean rented=false;

    public Place(String name, Category category, Host host, int numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }
    public Place() {}

    public void setRented(){
        rented=true;
    }
    public void removeRented(){
        rented=false;
    }
    public boolean getRented(){
        return rented;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Host getHost() {
        return host;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }
}
