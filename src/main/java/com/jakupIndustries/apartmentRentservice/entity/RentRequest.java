package com.jakupIndustries.apartmentRentservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Setter
@Getter
@Entity
@Table(name = "rent_requests")
public class RentRequest{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="apartment_id")
    @JsonIgnoreProperties({"rentRequests"})
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties({"rentedApartments","ownedApartments","roles","rentRequests"})
    private User user;

    public RentRequest() {
    }

    public RentRequest(Apartment apartment, User user) {
        this.apartment=apartment;
        this.user=user;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
