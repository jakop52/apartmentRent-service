package com.jakupIndustries.apartmentRentservice.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "apartments", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private float area;
    private int floor;
    private int rooms;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    //@JsonBackReference
    @JsonIgnoreProperties({"rentedApartments","ownedApartments","roles"})
    private User owner;

    @ManyToOne
    @JoinColumn(name = "rentier_id", nullable = true)
    //@JsonBackReference
    @JsonIgnoreProperties({"rentedApartments","ownedApartments","roles"})
    private User rentier;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getRentier() {
        return rentier;
    }

    public void setRentier(User rentier) {
        this.rentier = rentier;
    }

    public Apartment() {
    }

    public Apartment(long id, String name, String address, float area, int floor, int rooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.area = area;
        this.floor = floor;
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}