package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Address;
import com.hussam.carsAuction.security.userService.UserDetailsImp;

public interface AddressServiceI {

    Address addAddress(UserDetailsImp currentUser, Address address);
}
