package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.payload.request.SignUpRequest;
import com.hussam.carsAuction.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceI userService;

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) throws Exception {

       return ResponseEntity.ok(userService.registerUser(signUpRequest));
    }

    @GetMapping("/user/{id}")
    ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
