package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.exception.NotFoundException;
import com.hussam.carsAuction.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceI{

    @Autowired
    private CarRepository carRepository;


    @Override
    public Car getCarById(Long id) {
         Optional<Car> car = carRepository.findById(id);
        return car.orElseThrow(()-> new NotFoundException("car with id: "+ id+ " not found"));
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars()  {
        Date date = new Date();
        return carRepository.findAllByAuctionEndGreaterThanEqual(date);
    }
}
