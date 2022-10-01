package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.service.BidServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BidController {

    private BidServiceI bidService;

    public BidController(BidServiceI bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/bid")
    public ResponseEntity<?> addBidToCar(@RequestParam("car_id") Long car_id, @RequestParam("user_id") Long user_id , @RequestParam("amount") double amount)  {
            return new ResponseEntity<>(bidService.addBid(car_id,user_id,amount), HttpStatus.CREATED);


    }
}
