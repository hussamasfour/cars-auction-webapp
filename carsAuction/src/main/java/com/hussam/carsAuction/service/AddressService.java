package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Address;
import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.repository.AddressRepository;
import com.hussam.carsAuction.repository.UserRepository;
import com.hussam.carsAuction.security.userService.UserDetailsImp;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

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

            String email = currentUser.getUsername();
            Optional<User> user = userRepository.findUserByEmail(email);
            user.get().setAddress(address);
            address.setUser(user.get());

            return addressRepository.save(address);

//        if(user.isPresent() && user.get().getAddress() !=null){
//            Address address1 = user.get().getAddress();
////            address1.setUser(user.get());
//            address1.setStreet(address.getStreet());
//            address1.setCity(address.getCity());
//            address1.setZipcode(address.getZipcode());
////            user.get().setAddress(address1);
//            addressRepository.save(address);
//        }

    }
}
