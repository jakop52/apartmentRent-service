package com.jakupIndustries.apartmentRentservice.controller;

import com.jakupIndustries.apartmentRentservice.entity.Apartment;
import com.jakupIndustries.apartmentRentservice.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/apartments")
public class ApartmentController {
    @Autowired
    private ApartmentRepository apartmentRepository;


    @GetMapping
    public List<Apartment> getAllApartments() {
        return this.apartmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Apartment> getApartmentById(@PathVariable(value = "id") Long id) {
        return this.apartmentRepository.findById(id);
    }


    @PostMapping
    public Apartment createApartment(@RequestBody Apartment apartment) {
        return this.apartmentRepository.save(apartment);
    }

    //USUWANIE APARTAMENTU PO ID
}
