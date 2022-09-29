package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.payload.request.LoginRequest;
import com.hussam.carsAuction.payload.request.SignUpRequest;
import com.hussam.carsAuction.payload.response.SignInResponse;

public interface UserServiceI {

        /**
         * method to find user by id
         * @param user_id
         * @return
         */
        User getUserById(Long user_id);

        /**
         * Method to get user by email
         * @param email
         * @return
         */
        User getUserByEmail(String email);

        /**
         * Method to sign up new user
         * @param user
         * @return the newly created user
         */
        User registerUser(SignUpRequest user);

        /**
         * Mehtod to login user using currect email and password
         * @param loginRequest
         * @return logged in user
         */
        SignInResponse login(LoginRequest loginRequest);
}
