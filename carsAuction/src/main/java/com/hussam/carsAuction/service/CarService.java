package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.repository.CarRepository;
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
