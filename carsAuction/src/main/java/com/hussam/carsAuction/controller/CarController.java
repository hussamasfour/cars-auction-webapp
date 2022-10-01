package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.service.CarServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CarController {

    private final CarServiceI carService;
    @Autowired
    public CarController(CarServiceI carService) {
        this.carService = carService;
    }

    @PostMapping("/car")
    public ResponseEntity<?> addCar(@RequestBody Car car){
        carService.addCar(car);
        return new ResponseEntity<>("New car added to the auction", HttpStatus.CREATED);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id){
        Car car = carService.getCarById(id);

        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    @GetMapping("/car")
    public ResponseEntity<?> getAllCars() throws ParseException {
        List<Car> carList = carService.getAllCars();
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/search-car")
    public ResponseEntity<?> searchCars(@RequestParam("query") String query){
        List<Car> cars = carService.searchCars(query);

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
