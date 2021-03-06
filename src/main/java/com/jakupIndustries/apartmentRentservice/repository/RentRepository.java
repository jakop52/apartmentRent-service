package com.jakupIndustries.apartmentRentservice.repository;


import com.jakupIndustries.apartmentRentservice.entity.Apartment;
import com.jakupIndustries.apartmentRentservice.entity.RentRequest;
import com.jakupIndustries.apartmentRentservice.entity.User;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<RentRequest, Long> {
    List<RentRequest> findRentsByApartment(Apartment apartment);
    List<RentRequest> findRentsByUser(User user);

}
