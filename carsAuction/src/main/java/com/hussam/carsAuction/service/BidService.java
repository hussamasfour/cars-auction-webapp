package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Bid;
import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.exception.InvalidBidException;
import com.hussam.carsAuction.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public Bid addBid(Long car_id, Long user_id, double amount) {
        Car selected_car = carService.getCarById(car_id);
        User user = userService.getUserById(user_id);

        if(new Date().compareTo(selected_car.getAuctionEnd()) >0){
            throw new InvalidBidException("The auction for the selected car is ended!");
        }
        Double highestBid = bidRepository.findHighestBid(car_id);

        if(highestBid !=null && amount <= highestBid){
           throw new InvalidBidException("Your bid is not the highest bid");
       }
        Bid bid = new Bid();
        bid.setAmount(amount);
        bid.setCar(selected_car);
        bid.setUser(user);
        bid.setBidDate(new Date());

        return bidRepository.save(bid);
    }
}
