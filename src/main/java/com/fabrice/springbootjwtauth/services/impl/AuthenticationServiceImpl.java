package com.fabrice.springbootjwtauth.services.impl;

import com.fabrice.springbootjwtauth.dto.JWTAuthResponse;
import com.fabrice.springbootjwtauth.dto.SigninRequest;
import com.fabrice.springbootjwtauth.dto.SignupRequest;
import com.fabrice.springbootjwtauth.models.Role;
import com.fabrice.springbootjwtauth.models.User;
import com.fabrice.springbootjwtauth.repositories.UserRepository;
import com.fabrice.springbootjwtauth.services.AuthenticationService;
import com.fabrice.springbootjwtauth.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signup(SignupRequest signupRequest){
        User user = new User();
        user.setFirstname(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setEmail(signupRequest.getEmail());
        user.setRole(Role.USER); // by default, all users created will have USER Role

        //hash user password
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        //save our user to db
      return userRepository.save(user);
    }

    public JWTAuthResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));
        var user  = userRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt  =  jwtService.generateToken(user);
        var refreshToken  =  jwtService.generateRefreshToken(new HashMap<>(),user);

        JWTAuthResponse jwtAuthResponse =  new JWTAuthResponse();

        jwtAuthResponse.setToken(jwt);
        jwtAuthResponse.setRefreshToken(refreshToken);

        return jwtAuthResponse;

    }
}
