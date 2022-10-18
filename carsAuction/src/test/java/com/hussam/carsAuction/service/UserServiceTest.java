package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;
    private User user1;
    private User user2;

    @BeforeEach
    public void setup(){
        user1 = new User();
        user1.setId(1L);
        user1.setEmail("test@gmail.com");
        user1.setFirstName("test");
        user1.setLastName("test");
        user1.setPassword("123456");

    }

    @Test
    public void testGetUserByEmail_success(){
        when(userRepository.findUserByEmail("test@gmail.com")).thenReturn(Optional.ofNullable(user1));

        assertThat(userService.getUserByEmail("test@gmail.com")).isEqualTo(user1);

    }

    @Test
    public void testGetUserById_success(){
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user1));

        assertThat(userService.getUserById(1L)).isEqualTo(user1);


    }
}
