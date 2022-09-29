package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.service.CarServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarServiceI carService;

    @PostMapping("/car")
    public ResponseEntity<?> addCar(@RequestBody Car car){
        carService.addCar(car);
        return  ResponseEntity.ok("New car added to the auction");
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id){
        Car car = carService.getCarById(id);

        return ResponseEntity.ok(car);
    }

    @GetMapping("/car")
    public ResponseEntity<?> getAllCars() throws ParseException {
        List<Car> carList = carService.getAllCars();
        return ResponseEntity.ok(carList);
    }
}
