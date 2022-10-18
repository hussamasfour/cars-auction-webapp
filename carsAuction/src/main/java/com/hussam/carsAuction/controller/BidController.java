package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.payload.request.BidDTO;
import com.hussam.carsAuction.security.annotation.CurrentUser;
import com.hussam.carsAuction.security.userService.UserDetailsImp;
import com.hussam.carsAuction.service.BidServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class BidController {

    private final BidServiceI bidService;

    @Autowired
    public BidController(BidServiceI bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/bid")
    public ResponseEntity<?> addBidToCar(@RequestBody BidDTO bidDTO, @CurrentUser UserDetailsImp currentUser)  {
            log.info("Inside the addBidToCar method in BidController!");
            Long car_id = bidDTO.getCarId();
            Double amount = bidDTO.getAmount();
            return new ResponseEntity<>(bidService.addBid(car_id,currentUser,amount), HttpStatus.CREATED);
    }
}
