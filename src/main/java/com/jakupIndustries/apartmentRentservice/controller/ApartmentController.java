package com.jakupIndustries.apartmentRentservice.controller;

import com.jakupIndustries.apartmentRentservice.entity.Apartment;
import com.jakupIndustries.apartmentRentservice.repository.ApartmentRepository;
import com.jakupIndustries.apartmentRentservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/apartments")
public class ApartmentController {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<Apartment> getAllApartments() {
        return this.apartmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Apartment> getApartmentById(@PathVariable(value = "id") Long id) {
        return this.apartmentRepository.findById(id);
    }

    @GetMapping("/notRented")
    public List<Apartment> getNotRentedApartments(){return this.apartmentRepository.findByRentier(null);}


    @PostMapping("/forced")
    public Apartment createApartment(@RequestBody Apartment apartment) {
        return this.apartmentRepository.save(apartment);
    }

    @PostMapping()
    public Apartment createNewApartment(@CurrentSecurityContext(expression = "authentication?.name")
                                                String email, @RequestBody Apartment apartment) {
        Apartment newApartment = apartment;
        newApartment.setOwner(null);
        newApartment.setOwner(this.userRepository.findByEmail(email).get());

        return this.apartmentRepository.save(newApartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApartmentByID(@CurrentSecurityContext(expression = "authentication?.name")
                                                         String email, @PathVariable(value = "id") Long id) {
        Apartment apartment = this.apartmentRepository.findById(id).get();
        if (apartment.getOwner().getId() == this.userRepository.findByEmail(email).get().getId()) {
            this.apartmentRepository.delete(apartment);//TODO Rentier id must be null to delete
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
}
