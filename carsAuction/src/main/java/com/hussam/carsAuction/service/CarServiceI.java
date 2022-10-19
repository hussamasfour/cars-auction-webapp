package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Car;

import java.util.List;

/**
 * an interface to provide service methods for car entity
 */
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
     * method to get all active cars
     * @return list of cars
     */
        List<Car> getAllCars();

    /**
     * Method to search for car
     * @param query
     * @return list of cars that matched the query
     */
    List<Car> searchCars(String query);

    /**
     * remove car with selected id
     * @param id
     */
    void removeCar(Long id);
}

