package com.hussam.carsAuction.security.userService;

import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * class for loading the user data by the giving email
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found for email " + email);
        }

        return UserDetailsImp.build(user.get());
    }
}
