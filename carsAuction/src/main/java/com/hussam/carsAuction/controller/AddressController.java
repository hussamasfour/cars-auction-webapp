package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.entity.Address;
import com.hussam.carsAuction.security.annotation.CurrentUser;
import com.hussam.carsAuction.security.userService.UserDetailsImp;
import com.hussam.carsAuction.service.AddressServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {
    private final AddressServiceI addressService;

    @Autowired
    public AddressController(AddressServiceI addressService) {
        this.addressService = addressService;
    }

    /**
     * End point to handle request to add an address for current active user
     * @param address
     * @param currentUser
     * @return
     */
    @PostMapping("/add-address")
    public ResponseEntity<?> addUserAddress( @Valid @RequestBody Address address, @CurrentUser UserDetailsImp currentUser){
        log.info("inside addUserAddress in the addressController");
        Address createdAddress = addressService.addAddress(currentUser, address);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }
}
