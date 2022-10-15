package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.payload.request.LoginRequest;
import com.hussam.carsAuction.payload.request.SignUpRequest;
import com.hussam.carsAuction.payload.response.SignInResponse;
import com.hussam.carsAuction.service.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserServiceI userService;

    @Autowired
    public UserController(UserServiceI userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    ResponseEntity<?> registerUser( @Valid @RequestBody SignUpRequest signUpRequest) {
        log.info("Inside registerUser method for class userController");
       return ResponseEntity.ok(userService.registerUser(signUpRequest));
    }

    @PostMapping("/login")
    ResponseEntity<?> loginUser( @Valid @RequestBody LoginRequest loginRequest){
       SignInResponse signInResponse  =userService.login(loginRequest);
        log.info("Inside loginUser method for class userController");
        return new ResponseEntity<>(signInResponse, HttpStatus.OK) ;
    }
    @GetMapping("/user/{id}")
    ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        log.info("Inside getUserById method for class userController");
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
