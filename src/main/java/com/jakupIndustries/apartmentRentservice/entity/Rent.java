package com.jakupIndustries.apartmentRentservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long rentierId;
    private long tenantId;
    private long apartmentId;

}
