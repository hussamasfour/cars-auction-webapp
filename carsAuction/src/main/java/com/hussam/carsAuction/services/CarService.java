package com.hussam.carsAuction.services;

import com.hussam.carsAuction.models.Car;
import com.hussam.carsAuction.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService implements CarServiceI{

    @Autowired
    private CarRepository carRepository;


    @Override
    public Car getCarById(Long id) {
         Optional<Car> car = carRepository.findById(id);
        return car.orElse(null);
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }
}
