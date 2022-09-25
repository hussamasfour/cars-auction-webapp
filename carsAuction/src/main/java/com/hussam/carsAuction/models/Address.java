package com.hussam.carsAuction.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull
    private String street;
    @Column(nullable = false)
    @NotNull
    private String city;
    @NotNull
    @Column(nullable = false)
    private String state;
    @Column(nullable = false, length = 5)
    @NotNull
    private String zipcode;

}
