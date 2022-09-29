package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.payload.request.LoginRequest;
import com.hussam.carsAuction.payload.request.SignUpRequest;

public interface UserServiceI {
        User getUserById(Long user_id);
        User getUserByEmail(String email);
        User registerUser(SignUpRequest user);
        User login(LoginRequest loginRequest);
}
