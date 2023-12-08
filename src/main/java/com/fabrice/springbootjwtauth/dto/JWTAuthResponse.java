package com.fabrice.springbootjwtauth.dto;

import lombok.Data;

@Data //generating getters and setters
public class JWTAuthResponse {
    private String token;
    private String refreshToken;
}
