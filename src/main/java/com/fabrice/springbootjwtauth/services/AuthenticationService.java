package com.fabrice.springbootjwtauth.services;

import com.fabrice.springbootjwtauth.dto.JWTAuthResponse;
import com.fabrice.springbootjwtauth.dto.SigninRequest;
import com.fabrice.springbootjwtauth.dto.SignupRequest;
import com.fabrice.springbootjwtauth.models.User;

public interface AuthenticationService {
    User signup(SignupRequest signupRequest);
    JWTAuthResponse signin(SigninRequest signinRequest);
}
