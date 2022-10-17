package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Bid;
import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.exception.InvalidBidException;
import com.hussam.carsAuction.repository.BidRepository;
import com.hussam.carsAuction.security.userService.UserDetailsImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class BidService implements BidServiceI{

    private final BidRepository bidRepository;

    private final CarServiceI carService;

    private final UserServiceI userService;

    @Autowired
    public BidService(BidRepository bidRepository, CarServiceI carService, UserServiceI userService) {
        this.bidRepository = bidRepository;
        this.carService = carService;
        this.userService = userService;
    }

    @Override
    public Bid addBid(Long car_id, UserDetailsImp currentUser, double amount) {
        log.info("inside addBid method in bidService class");

        if(currentUser == null){
            log.error("you have to login first to be able to place bid");
            throw new InvalidBidException("you have to login first to place a bid");
        }
        log.info("Looking for car with id:" + car_id);
        Car selected_car = carService.getCarById(car_id);
        log.info("Looking for User with Email: "+ currentUser.getEmail());
        User user = userService.getUserByEmail(currentUser.getEmail());

        if(new Date().compareTo(selected_car.getAuctionEnd()) >0){

            throw new InvalidBidException("The auction for the selected car is ended!");
        }
        log.info(String.format("Getting the highest bid for the car  with id: %d ", car_id ));
        Double highestBid = bidRepository.findHighestBid(car_id);

        if(highestBid !=null && amount <= highestBid){
           log.error(String.format("the bid amount: %f is less than the highest bid: %f", amount, highestBid));
           throw new InvalidBidException("Your bid is not the highest");
       }

        log.info(String.format("creating the bid with amount: %f for car with id: %d", amount,car_id));
        Bid bid = new Bid();
        bid.setAmount(amount);
        bid.setCar(selected_car);
        bid.setUser(user);
        bid.setBidDate(new Date());
        log.info("Saving the bid details to database");
        return bidRepository.save(bid);
    }
}
