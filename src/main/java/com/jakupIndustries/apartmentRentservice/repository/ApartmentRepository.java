package com.jakupIndustries.apartmentRentservice.repository;

import com.jakupIndustries.apartmentRentservice.entity.Apartment;
import com.jakupIndustries.apartmentRentservice.entity.Role;
import com.jakupIndustries.apartmentRentservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Optional<Apartment> findByName(String name);
    Optional<Apartment> findById(int id);
    List<Apartment> findByRentier(User user);

}
