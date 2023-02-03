package com.app.dto;

import com.app.entities.User;

import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;

public class UserResponse {
    private String firstName;
    private String lastName;
    private int age;

    public UserResponse(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public static UserResponse mapperUserToUserResponse(User u) {
        return new UserResponse(
                u.getFirstName(),
                u.getLastName(),
                ageCalculator(u.getDateOfBirth()));
    }

    public static int ageCalculator(Date dateOfBirth) {
        return Period
                .between(dateOfBirth.toLocalDate(), LocalDate.now())
                .getYears();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
