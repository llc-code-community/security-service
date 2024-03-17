package com.securityservice.model.dto;

import java.util.List;

public record UserDTO(
        Integer userId,
        String firstname,
        String lastname,
        String email,
        String password,
        List<String> roles
) {
}
