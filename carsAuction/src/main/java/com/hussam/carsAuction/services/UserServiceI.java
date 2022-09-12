package com.hussam.carsAuction.services;

import com.hussam.carsAuction.models.User;

public interface UserServiceI {
        User getUserById(Long user_id);
        User getUserByEmail(String email);
        public boolean registerUser(User user);
}
