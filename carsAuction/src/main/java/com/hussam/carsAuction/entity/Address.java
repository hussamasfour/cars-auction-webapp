package com.hussam.carsAuction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "street is required")
    private String street;
    @Column(nullable = false)
    @NotBlank(message = "City is required")
    private String city;
    @Column(nullable = false)
    @NotBlank(message = "State is required")
    private String state;
    @Column(nullable = false, length = 5)
    @NotBlank(message = "ZipCode is required")
    private String zipcode;
    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private User user;
}
