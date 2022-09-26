package com.hussam.carsAuction.controller;

import com.hussam.carsAuction.payload.request.SignUpRequest;
import com.hussam.carsAuction.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceI userService;

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) throws Exception {

       return ResponseEntity.ok(userService.registerUser(signUpRequest));
    }

}
