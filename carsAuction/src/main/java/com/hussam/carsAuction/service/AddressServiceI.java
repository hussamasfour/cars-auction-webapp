package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Address;
import com.hussam.carsAuction.security.userService.UserDetailsImp;

/**
 * an interface to provide service methods for Address entity
 */
public interface AddressServiceI {

    /**
     * Method to create new address for currently logged-in user
     * @param currentUser
     * @param address
     * @return the newly created address
     */
    Address addAddress(UserDetailsImp currentUser, Address address);
}
