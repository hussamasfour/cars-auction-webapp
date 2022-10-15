package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.security.annotation.CurrentUser;
import com.hussam.carsAuction.security.userService.UserDetailsImp;
import com.hussam.carsAuction.service.CarServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarServiceI carService;
    @Autowired
    public CarController(CarServiceI carService) {
        this.carService = carService;
    }

    @PostMapping("/car")
    @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<?> addCar(@RequestBody Car car, @CurrentUser UserDetailsImp currentUser){
        log.info("Inside addCar method for carController class");
        carService.addCar(car);
        return new ResponseEntity<>("New car added to the auction", HttpStatus.CREATED);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id){
        log.info("Inside getCarById method for carController class");
        Car car = carService.getCarById(id);

        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    @GetMapping("/car")
    public ResponseEntity<?> getAllCars() throws ParseException {
        log.info("Inside getAllCars method for carController class");
        List<Car> carList = carService.getAllCars();
        if(carList.isEmpty()){
            log.error("There is no cars available for sale");
            throw new ResourceNotFoundException("There is no cars available for sale");
        }
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/search-car")
    public ResponseEntity<?> searchCars(@RequestParam("query") String query){
        log.info("Inside searchCars method for carController class");
        List<Car> cars = carService.searchCars(query);

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @DeleteMapping("/car")
    public ResponseEntity<?> removeCar(@RequestParam("id") Long id){
        log.info("Inside removeCar method for carController class");
        carService.removeCar(id);
        return new ResponseEntity<>("Selected Car is deleted successfully", HttpStatus.OK);
    }
}
