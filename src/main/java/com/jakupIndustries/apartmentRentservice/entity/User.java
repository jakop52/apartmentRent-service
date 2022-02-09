package com.jakupIndustries.apartmentRentservice.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

//@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;

    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    private Set<Role> roles;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    //@JsonManagedReference
    @JsonIgnoreProperties()
    private Set<Apartment> ownedApartments;

    @OneToMany(mappedBy = "rentier", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    //@JsonManagedReference
    //@JsonIgnoreProperties({"owner","rentier"})
    @JsonIgnoreProperties()
    private Set<Apartment> rentedApartments;

    //TODO rent request should be visible in JSON
    @OneToMany(mappedBy = "user")
    private Set<RentRequest> rentRequests;

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Apartment> getOwnedApartments() {
        return ownedApartments;
    }

    public void setOwnedApartments(Set<Apartment> ownedApartments) {
        this.ownedApartments = ownedApartments;
    }

    public Set<Apartment> getRentedApartments() {
        return rentedApartments;
    }

    public void setRentedApartments(Set<Apartment> rentedApartments) {
        this.rentedApartments = rentedApartments;
    }

    public Set<RentRequest> getRentRequests() {
        return rentRequests;
    }

    public void setRentRequests(Set<RentRequest> rentRequests) {
        this.rentRequests = rentRequests;
    }
}