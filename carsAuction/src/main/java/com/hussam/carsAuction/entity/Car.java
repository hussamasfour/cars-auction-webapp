package com.hussam.carsAuction.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "car")
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

    private Double highestBid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date AuctionStart;
    @Future
    @Temporal(TemporalType.TIMESTAMP)
    private Date AuctionEnd;
    @OneToMany(mappedBy = "car")
    private Set<Bid> bids = new HashSet<>();
    @ManyToOne
    private User user;
}
