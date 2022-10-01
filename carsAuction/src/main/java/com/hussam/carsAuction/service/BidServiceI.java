package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Bid;

public interface BidServiceI {
    Bid addBid(Long car_id, Long user_id, double amount) ;
}
