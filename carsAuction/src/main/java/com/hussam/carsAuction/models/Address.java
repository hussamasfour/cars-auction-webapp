package com.hussam.carsAuction.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Address {

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
