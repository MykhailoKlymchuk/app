package com.app.dto;

import com.app.entities.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseUnitTest {

    @Test
    void mapperUserToUserResponse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User u = new User(1l, "first_name", "last_name", Date.valueOf("2020-01-01"));
        UserResponse ur = UserResponse.mapperUserToUserResponse(u);
        assertEquals(ur.getFirstName(), u.getFirstName());
        assertEquals(ur.getLastName(), u.getLastName());
        assertEquals(
                ur.getAge(),
                (int) UserResponse.class
                        .getMethod("ageCalculator", Date.class)
                        .invoke(null, u.getDateOfBirth())
        );
    }
}