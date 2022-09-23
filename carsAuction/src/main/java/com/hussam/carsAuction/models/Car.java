package com.hussam.carsAuction.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false, length = 5)
    private String model;
    @Column(nullable = false, length = 4)
    private int year;
    @Positive
    @Column(nullable = false)
    private int numberOfSeats;
    @Column(nullable = false, unique = true,updatable = false)
    private String vinNumber;
    @Column(nullable = false)
    private String fuelType;
    @Column(nullable = false)
    private String fuelCapacity;
    @Column(nullable = false)
    private String imagesLink;

}
