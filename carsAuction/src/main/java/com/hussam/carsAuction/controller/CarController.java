package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.security.annotation.CurrentUser;
import com.hussam.carsAuction.security.userService.UserDetailsImp;
import com.hussam.carsAuction.service.CarServiceI;
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

    @DeleteMapping("/car")
    public ResponseEntity<?> removeCar(@RequestParam("id") Long id){
        carService.removeCar(id);
        return new ResponseEntity<>("Selected Car is deleted successfully", HttpStatus.OK);
    }
}
