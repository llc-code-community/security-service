package com.securityservice.dto.response;

import lombok.Builder;

@Builder
public class TokenResponse {
    private String jwt;
}
