package com.fabrice.springbootjwtauth.dto;


import lombok.Data;

@Data //lambok annotation for getting setters&getters
public class SignupRequest {
    //this is how the signup request will be formatted
    private String fName;
    private String lName;
    private String email;
    private String password;
}
