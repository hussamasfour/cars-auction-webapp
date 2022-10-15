package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Bid;
import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.exception.InvalidBidException;
import com.hussam.carsAuction.exception.NotFoundException;
import com.hussam.carsAuction.repository.BidRepository;
import com.hussam.carsAuction.security.userService.UserDetailsImp;
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
    public Bid addBid(Long car_id, UserDetailsImp currentUser, double amount) {
        Car selected_car = carService.getCarById(car_id);
        if(currentUser == null){
            throw new InvalidBidException("you have to login first to place a bid");
        }
        User user = userService.getUserByEmail(currentUser.getEmail());

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
