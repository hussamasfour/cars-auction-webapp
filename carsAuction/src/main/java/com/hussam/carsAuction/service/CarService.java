package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.exception.NotFoundException;
import com.hussam.carsAuction.exception.ResourceAlreadyInUseException;
import com.hussam.carsAuction.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Class to provide implementation for car service interface
 */
@Slf4j
@Service
public class CarService implements CarServiceI{

    private final CarRepository carRepository;
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car getCarById(Long id) {
        log.info("inside getCarById method for carService class");
        log.info(String.format("Searching for car with id: %d", id));
         Optional<Car> car = carRepository.findById(id);
        return car.orElseThrow(()->{
                log.error("There is no car found with id: %d " + id);
                throw new NotFoundException("car with id: "+ id+ " not found");
        });
    }

    @Override
    public void addCar(Car car) {
        log.info("Inside addCar method in carService class");
        if( existsByVinNumber(car.getVinNumber()) ){
            log.error("There is car already exist with this Vin Number: "+ car.getVinNumber());
            throw new ResourceAlreadyInUseException("This Vin Number", "another car" , car.getVinNumber());
        }
        log.info("Storing the new car with vin number"+ car.getVinNumber()+" to the database");
        Date date = new Date();
        car.setAuctionStart(date);
        carRepository.save(car);
    }

    /**
     * check car vin number if it is already in use in our database repository
     * @param vinNumber
     * @return true if the vinNumber exists else false
     */
    private boolean existsByVinNumber(String vinNumber) {
        log.info("Checking if this vinNUmber: " + vinNumber +" is used with different car in the database");
       return carRepository.existsByVinNumber(vinNumber);
    }

    @Override
    public List<Car> getAllCars()  {
        log.info("Inside getAllCars method for class carService");
        Date date = new Date();
        log.info("Retrieving all cars which the sale is still active");
        return carRepository.findAllByAuctionEndGreaterThanEqual(date);
    }

    @Override
    public List<Car> searchCars(String query) {
        log.info("Inside searchCar method in CarService class");
        Date d = new Date();
        log.info("Searching for all cars that match selected query: "+ query);
        return carRepository.searchCars(query, d);
    }

    @Override
    public void removeCar(Long id) {
        log.info("Inside removeCar method in CarService class");
        log.info("Searching for car with id: " + id+ " to delete it from the database");
        Car car = getCarById(id);
        if(car == null){
            log.error("There is no car with id: "+ id);
            throw new NotFoundException("Car with id:" + id + " does not exit!!");
        }
        log.info("Deleting the car with id: "+ id);
        carRepository.delete(car);
    }
}
