package com.hussam.carsAuction.services;

import com.hussam.carsAuction.models.Car;

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
