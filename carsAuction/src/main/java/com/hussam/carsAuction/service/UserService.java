package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Role;
import com.hussam.carsAuction.entity.Type;
import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.payload.request.SignUpRequest;
import com.hussam.carsAuction.repository.RoleRepository;
import com.hussam.carsAuction.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * class to provide implementation for user service interface
 */
@Service
public class UserService implements UserServiceI {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
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
    public User registerUser(SignUpRequest user){

        if(validateEmail(user.getEmail())) {
            Set<Role> roles = new HashSet<>();
            Role role = roleRepository.findByType(Type.USER);
            roles.add(role);

            User newUser = new User();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setRole(roles);

            return userRepository.save(newUser);

        }
        return null;
    }


}
