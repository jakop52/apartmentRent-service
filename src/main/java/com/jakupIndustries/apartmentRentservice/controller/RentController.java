package com.jakupIndustries.apartmentRentservice.controller;

import com.jakupIndustries.apartmentRentservice.entity.Apartment;
import com.jakupIndustries.apartmentRentservice.entity.RentRequest;
import com.jakupIndustries.apartmentRentservice.entity.User;
import com.jakupIndustries.apartmentRentservice.payload.RentRequestDTO;
import com.jakupIndustries.apartmentRentservice.repository.ApartmentRepository;
import com.jakupIndustries.apartmentRentservice.repository.RentRepository;
import com.jakupIndustries.apartmentRentservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rents")
public class RentController {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @PostMapping //TODO USERID IS UNNNECESSARY - I CAN GET IT FROM CurrentSecurityContext && USER CAN ADD MORE THEN ONE REQUEST PER APARTMENT
    public ResponseEntity<String> postRentRequest(@CurrentSecurityContext(expression = "authentication?.name")
                                                               String email, @RequestBody RentRequestDTO rentRequestDTO) {
        User user = this.userRepository.findByEmail(email).get();
        Apartment apartment = this.apartmentRepository.findById(rentRequestDTO.getApartmentID()).get();

        if (rentRequestDTO.getUserID() == user.getId()) {
            if (apartment.getOwner() != user) {
                this.rentRepository.save(new RentRequest(apartment, user));
                return new ResponseEntity<>("Rent request created",HttpStatus.OK);
            } else return new ResponseEntity<>("You cannot request own apartment",HttpStatus.UNAUTHORIZED);

        } else return new ResponseEntity<>("",HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/{apartmentID}")
    public List<RentRequest> getRentRequestsByApartmentID(@PathVariable(value = "apartmentID") Long apartmentID) {
        return this.rentRepository.findRentsByApartment(this.apartmentRepository.findById(apartmentID).get());

    }

    @GetMapping()
    public List<RentRequest> getRentRequestsByCurrentUser(@CurrentSecurityContext(expression = "authentication?.name")
                                                                  String email) {
        return this.rentRepository.findRentsByUser(this.userRepository.findByEmail(email).get());
    }

    @DeleteMapping("/delete/{rentID}") //TODO TESTED - error
    public ResponseEntity<String> deleteRentRequest(@CurrentSecurityContext(expression = "authentication?.name")
                                                            String email, @PathVariable(value = "rentID") Long rentID) {
        User user = this.userRepository.findByEmail(email).get();
        RentRequest rentRequest = this.rentRepository.findById(rentID).get();
        if ((rentRequest.getApartment().getOwner() == user) || rentRequest.getUser() == user) {
            this.rentRepository.delete(rentRequest);
            return new ResponseEntity<>("RentRequest deleted", HttpStatus.OK);
        } else return new ResponseEntity<>("Only OWNER or CREATOR can delete rent request", HttpStatus.UNAUTHORIZED);
    }

    @PatchMapping("/accept/{rentID}")
    public ResponseEntity<String> acceptRentRequest(@CurrentSecurityContext(expression = "authentication?.name")
                                                            String email, @PathVariable(value = "rentID") Long rentID) {
        User user = this.userRepository.findByEmail(email).get();
        RentRequest rentRequest = this.rentRepository.findById(rentID).get();
        if (rentRequest.getApartment().getOwner() == user) {
            if (rentRequest.getApartment().getRentier() == null) {
                rentRequest.getApartment().setRentier(rentRequest.getUser());

                deleteAllRequestsByApartment(rentRequest.getApartment());

                return new ResponseEntity<>("Rent request accepted", HttpStatus.OK);
            } else return new ResponseEntity<>("This apartment is already rented", HttpStatus.NOT_ACCEPTABLE);

        } else return new ResponseEntity<>("Only OWNER can accept rent request", HttpStatus.UNAUTHORIZED);

    }


    private void deleteAllRequestsByApartment(Apartment apartment) {
        List<RentRequest> requestList = this.rentRepository.findRentsByApartment(apartment);
        this.rentRepository.deleteAll(requestList);
    }

    private void deleteAllRequestsByUser(User user) {
        List<RentRequest> requestList = this.rentRepository.findRentsByUser(user);
        this.rentRepository.deleteAll(requestList);
    }

}
