package com.app.services;

import com.app.dto.UserResponse;
import com.app.entities.User;
import com.app.repositories.UserRepository;
import com.app.services.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse getUserById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserResponse.mapperUserToUserResponse(u);
    }
}
