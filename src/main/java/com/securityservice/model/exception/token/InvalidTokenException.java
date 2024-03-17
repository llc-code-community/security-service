package com.securityservice.model.exception.token;

import io.jsonwebtoken.JwtException;

public class InvalidTokenException extends JwtException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
