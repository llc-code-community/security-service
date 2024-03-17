package com.securityservice.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record AuthRequest(
        @Email @NotNull
        String email,

        @NotNull
        String password
) {}
