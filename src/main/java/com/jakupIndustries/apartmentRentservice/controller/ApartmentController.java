package com.jakupIndustries.apartmentRentservice.controller;

import com.jakupIndustries.apartmentRentservice.entity.Apartment;
import com.jakupIndustries.apartmentRentservice.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
public class ApartmentController {
    @Autowired
    private ApartmentRepository apartmentRepository;


    @GetMapping
    public List<Apartment> getAllApartments(){
        return this.apartmentRepository.findAll();
    }

    @PostMapping
    public Apartment createUser(@RequestBody Apartment apartment) {
        return this.apartmentRepository.save(apartment);
    }
}
