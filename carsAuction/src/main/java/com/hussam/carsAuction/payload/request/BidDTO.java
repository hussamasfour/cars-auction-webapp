package com.hussam.carsAuction.payload.request;

import lombok.Data;

@Data
public class BidDTO {
    private Double amount;
    private Long carId;
}
