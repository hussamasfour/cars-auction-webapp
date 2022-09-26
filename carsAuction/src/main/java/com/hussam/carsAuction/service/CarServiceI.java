package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Car;

public interface CarServiceI {

    /**
     * method to find car by id
     * @param id
     * @return car object if the car exist in the database
     */
        Car getCarById(Long id);

    /**
     * method to add car to the auction
     * @param car
     */
    void addCar(Car car);
}