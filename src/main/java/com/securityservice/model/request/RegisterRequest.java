package com.securityservice.model.request;

public record RegisterRequest(
        Integer userId,
        String firstname,
        String lastname,
        String email,
        String password
) {
}
