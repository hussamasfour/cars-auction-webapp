package com.hussam.carsAuction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "bid")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date bidDate;

    private Double amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull

    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Car car;
}
