package com.hussam.carsAuction.entity;


import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "car")
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false, length = 4)
    private int year;
    @Column(nullable = false)
    private int numberOfSeats;
    @NotBlank(message = "VinNumber is required")
    @Column(nullable = false, unique = true,updatable = false)
    private String vinNumber;
    @Column(nullable = false)
    private String fuelType;
    @Column(nullable = false)
    private double fuelCapacity;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String drive;
    @Column(nullable = false)
    private String transmission;
    @Column(nullable = false)
    private String imagesLink;
    @Temporal(TemporalType.DATE)
    private Date auctionStart;
    @Temporal(TemporalType.DATE)
    private Date auctionEnd;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<Bid> bids = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
