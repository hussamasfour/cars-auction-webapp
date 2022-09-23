package com.hussam.carsAuction.services;

import com.hussam.carsAuction.models.User;
import com.hussam.carsAuction.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * class to provide implementation for user service interface
 */
@Service
public class UserService implements UserServiceI {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
    /**
     * Method toget user from the database using id
     * @param user_id
     * @return User Object if found and null if not
     */
    @Override
    public User getUserById(Long user_id){
        Optional<User> user = userRepository.findById(user_id);
        return user.orElse(null);
    }

    /**
     * method to find user by email
     * @param email
     * @return user if exist or null
     */
    @Override
    public User getUserByEmail(String email){
        if(validateEmail(email)) {
            User user = userRepository.findUserByEmail(email);
            return user;
        }
        return null;
    }

    /**
     * method to validate if email is valid or not
     * @param email
     * @return true or false
     */
    public boolean validateEmail(String email){
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Method to register new user into the database
     * @param user
     * @return true if successes or false
     */
    @Override
    public boolean registerUser(User user){
        if(validateEmail(user.getEmail())) {
//            user.setPassword(encodePassword(user.getPassword()));
             userRepository.save(user);
            return true;
        }
        return false;
    }

    /**
     * Method to encode password before saving into the database
     * @param password
     * @return encoded password
     */
//    private String encodePassword(String password){
//        return passwordEncoder.encode(password);
//    }
}
