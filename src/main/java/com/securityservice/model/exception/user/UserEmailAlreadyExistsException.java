package com.securityservice.model.exception.user;

import jakarta.persistence.EntityExistsException;

public class UserEmailAlreadyExistsException extends EntityExistsException {
    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}
