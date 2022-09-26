package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Role;
import com.hussam.carsAuction.entity.Type;
import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.exception.NotFoundException;
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
        return user.orElseThrow(()-> new NotFoundException("user with id: "+ user_id +" not found"));
    }

    /**
     * method to find user by email
     * @param email
     * @return user if exist or null
     */
    @Override
    public User getUserByEmail(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.orElseThrow(() -> new NotFoundException("User is not found with email " + email));
    }


    /**
     * Method to register new user into the database
     * @param user
     * @return true if successes or false
     */
    @Override
    public User registerUser(SignUpRequest user){
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


}
