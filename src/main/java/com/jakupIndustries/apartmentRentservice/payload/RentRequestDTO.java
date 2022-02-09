package com.jakupIndustries.apartmentRentservice.payload;

public class RentRequestDTO {
    private Long apartmentID;
    private Long userID;

    public Long getApartmentID() {
        return apartmentID;
    }

    public Long getUserID() {
        return userID;
    }
}
