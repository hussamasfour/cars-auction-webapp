package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Car;

import java.text.ParseException;
import java.util.List;

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

    /**
     * method to list all active cars
     */
        List<Car> getAllCars() throws ParseException;

    /**
     * Method to search for car
     * @param query
     * @return list of cars that matched the query
     */
    List<Car> searchCars(String query);
}

