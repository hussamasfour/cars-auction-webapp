package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Bid;
import com.hussam.carsAuction.security.userService.UserDetailsImp;

public interface BidServiceI {
    Bid addBid(Long car_id, UserDetailsImp currentUser, double amount) ;
}
