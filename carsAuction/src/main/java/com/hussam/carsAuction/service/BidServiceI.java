package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Bid;
import com.hussam.carsAuction.security.userService.UserDetailsImp;

/**
 * an interface to provide service methods for bid entity
 */
public interface BidServiceI {

    /**
     * method to add bid for selected car by the logged-in user
     * @param car_id
     * @param currentUser
     * @param amount
     * @return the newly created bid info
     */
    Bid addBid(Long car_id, UserDetailsImp currentUser, double amount) ;
}
