package com.jakupIndustries.apartmentRentservice.payload;


import com.jakupIndustries.apartmentRentservice.entity.Apartment;
import com.jakupIndustries.apartmentRentservice.entity.Role;
import java.util.Set;

public class UserDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
    private Set<Apartment> ownedApartments;
    private Set<Apartment> rentedApartments;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", ownedApartments=" + ownedApartments +
                ", rentedApartments=" + rentedApartments +
                '}';
    }
}
