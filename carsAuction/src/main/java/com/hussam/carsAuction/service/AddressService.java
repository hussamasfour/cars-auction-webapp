package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Address;
import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.exception.ResourceAlreadyInUseException;
import com.hussam.carsAuction.repository.AddressRepository;
import com.hussam.carsAuction.repository.UserRepository;
import com.hussam.carsAuction.security.userService.UserDetailsImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AddressService implements AddressServiceI{

    private UserRepository userRepository;

    private AddressRepository addressRepository;

    public AddressService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }


    /**
     * add an address information for current logged-in user
     * @param currentUser
     * @param address
     * @return the newly created address
     */
    @Override
    public Address addAddress(UserDetailsImp currentUser, Address address) {
            log.info("adding address to current logged in user");
            String email = currentUser.getEmail();
            log.info("check if the user exist!");
            Optional<User> user = userRepository.findUserByEmail(email);
            log.info("setting the address for user with email: " + email );
            if(user.get().getAddress() !=null){
                throw new ResourceAlreadyInUseException(user.get().getFirstName(), "address" ,user.get().getAddress().getStreet());
            }
            user.get().setAddress(address);
            address.setUser(user.get());
            log.info("saving the address to the database");
            return addressRepository.save(address);

    }
}
