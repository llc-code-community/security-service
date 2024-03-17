package com.securityservice.model.exception.token;

import io.jsonwebtoken.JwtException;

public class TokenAlreadyExpiredOrRevoked extends JwtException {
    public TokenAlreadyExpiredOrRevoked(String message) {
        super(message);
    }
}
