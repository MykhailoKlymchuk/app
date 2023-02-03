package com.app.services.exception;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException( final Long id) {
        super(MessageFormat.format("Could not find User with id: {0}", id));
    }

}
