package com.hussam.carsAuction;

import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;



    @Test
    public void testFindUserByEmail_success(){
       User user = new User();
       user.setEmail("test@gmail.com");
       user.setFirstName("test");
       user.setLastName("test");
       user.setPassword("12345678");


       userRepository.save(user);

       Optional<User> foundUser = userRepository.findUserByEmail(user.getEmail());

       assertThat(foundUser.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testExistUserByEmail_success(){
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setFirstName("test");
        user.setLastName("test");
        user.setPassword("12345678");
        userRepository.save(user);

        Assertions.assertTrue(userRepository.existsByEmail("test@gmail.com"));
    }
}
