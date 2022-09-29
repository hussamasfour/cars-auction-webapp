package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.payload.request.LoginRequest;
import com.hussam.carsAuction.payload.request.SignUpRequest;
import com.hussam.carsAuction.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceI userService;

    @Autowired
    public UserController(UserServiceI userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {

       return ResponseEntity.ok(userService.registerUser(signUpRequest));
    }

    @PostMapping("/login")
    ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.login(loginRequest));
    }
    @GetMapping("/user/{id}")
    ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
