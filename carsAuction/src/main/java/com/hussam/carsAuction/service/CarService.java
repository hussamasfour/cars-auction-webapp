package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.exception.NotFoundException;
import com.hussam.carsAuction.exception.ResourceAlreadyInUseException;
import com.hussam.carsAuction.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceI{


    private final CarRepository carRepository;
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public Car getCarById(Long id) {
         Optional<Car> car = carRepository.findById(id);
        return car.orElseThrow(()-> new NotFoundException("car with id: "+ id+ " not found"));
    }

    @Override
    public void addCar(Car car) {
        if( existsByVinNumber(car.getVinNumber()) ){
            throw new ResourceAlreadyInUseException("This Vin Number", "another car" , car.getVinNumber());
        }
        carRepository.save(car);
    }

    /**
     * check car vin number if it is already in use in our database repository
     * @param vinNumber
     * @return true if the vinNumber exists else false
     */
    private boolean existsByVinNumber(String vinNumber) {
       return carRepository.existsByVinNumber(vinNumber);
    }

    @Override
    public List<Car> getAllCars()  {
        Date date = new Date();
        return carRepository.findAllByAuctionEndGreaterThanEqual(date);
    }

    @Override
    public List<Car> searchCars(String query) {
        Date d = new Date();
        return carRepository.searchCars(query, d);
    }

    @Override
    public void removeCar(Long id) {
        Car car = getCarById(id);
        if(car == null){
            throw new NotFoundException("Car with id:" + id + " does not exit!!");
        }

        carRepository.delete(car);
    }
}
