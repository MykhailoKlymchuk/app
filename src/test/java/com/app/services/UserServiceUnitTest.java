package com.app.services;

import com.app.dto.UserResponse;
import com.app.entities.User;
import com.app.repositories.UserRepository;
import com.app.services.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceUnitTest {
    @Mock
    private UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    void getUserById() {
        User u = new User(1l, "first_name", "last_name", Date.valueOf("2020-01-01"));
        UserResponse ur = UserResponse.mapperUserToUserResponse(u);
        when(userService.getUserById(1l)).thenReturn(ur);
        assertEquals(userService.getUserById(1L).getFirstName(), ur.getFirstName());
        assertEquals(userService.getUserById(1L).getLastName(), ur.getLastName());
        assertEquals(userService.getUserById(1L).getAge(), ur.getAge());
    }

    @Test
    void getUserByIdWithException() {
        when(userService.getUserById(1l)).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1l));
    }

    @Test
    void getUserByIdServiceThroughRepository() {
        User u = new User(
                1l, "first_name", "last_name",
                Date.valueOf("2020-01-01")
        );
        when(userRepository.findById(1L)).thenReturn(Optional.of(u));

        assertEquals(u.getFirstName(), userRepository.findById(1L).get().getFirstName());
        assertEquals(u.getLastName(), userRepository.findById(1L).get().getLastName());
        assertEquals(u.getDateOfBirth(), userRepository.findById(1L).get().getDateOfBirth());

        UserResponse ur = UserResponse.mapperUserToUserResponse(userRepository.findById(1L).get());

        when(userService.getUserById(1l)).thenReturn(ur);
        assertEquals(userService.getUserById(1L).getFirstName(), ur.getFirstName());
        assertEquals(userService.getUserById(1L).getLastName(), ur.getLastName());
        assertEquals(userService.getUserById(1L).getAge(), ur.getAge());
    }
}