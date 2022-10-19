package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.entity.Car;

import com.hussam.carsAuction.service.CarServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Car Controller class  where all request for car handler
 */
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

    /**
     * handle request for posting a new car
     * @param car
     * @return
     */
    @PostMapping("/car")
    @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<?> addCar(@RequestBody Car car){
        log.info("Inside addCar method for carController class");
        carService.addCar(car);
        return new ResponseEntity<>("New car added to the auction", HttpStatus.CREATED);
    }

    /**
     * point to handle request for getting car with selected id
     * @param id
     * @return
     */
    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id){
        log.info("Inside getCarById method for carController class");
        Car car = carService.getCarById(id);

        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    /**
     * point to handle request for getting all cars available
     * @return
     */
    @GetMapping("/car")
    public ResponseEntity<?> getAllCars(){
        log.info("Inside getAllCars method for carController class");
        List<Car> carList = carService.getAllCars();
        if(carList.isEmpty()){
            log.error("There is no cars available for sale");
            throw new ResourceNotFoundException("There is no cars available for sale");
        }
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    /**
     * Point to handle request for searching for cars
     * @param query
     * @return
     */
    @GetMapping("/search-car")
    public ResponseEntity<?> searchCars(@RequestParam("query") String query){
        log.info("Inside searchCars method for carController class");
        List<Car> cars = carService.searchCars(query);

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    /**
     * point to remove car by given id
     * @param id
     * @return
     */
    @DeleteMapping("/car")
    public ResponseEntity<?> removeCar(@RequestParam("id") Long id){
        log.info("Inside removeCar method for carController class");
        carService.removeCar(id);
        return new ResponseEntity<>("Selected Car is deleted successfully", HttpStatus.OK);
    }
}
