package com.fabrice.springbootjwtauth.controllers;

import com.fabrice.springbootjwtauth.dto.JWTAuthResponse;
import com.fabrice.springbootjwtauth.dto.RefeshTokenRequest;
import com.fabrice.springbootjwtauth.dto.SigninRequest;
import com.fabrice.springbootjwtauth.dto.SignupRequest;
import com.fabrice.springbootjwtauth.models.User;
import com.fabrice.springbootjwtauth.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest){
        return ResponseEntity.ok(authenticationService.signup(signupRequest));
    }


    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> signup(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthResponse> refresh(@RequestBody RefeshTokenRequest refeshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refeshTokenRequest));
    }
}
