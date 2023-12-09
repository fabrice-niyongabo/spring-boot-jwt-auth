package com.fabrice.springbootjwtauth.dto;

import lombok.Data;

@Data //getters & setters
public class RefeshTokenRequest {
    private String token;
}
