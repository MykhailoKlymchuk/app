package com.app.controllers;

import com.app.dto.UserResponse;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable final Long id) {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);

    }

}
