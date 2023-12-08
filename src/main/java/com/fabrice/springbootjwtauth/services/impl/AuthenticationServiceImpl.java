package com.fabrice.springbootjwtauth.services.impl;

import com.fabrice.springbootjwtauth.dto.SignupRequest;
import com.fabrice.springbootjwtauth.models.Role;
import com.fabrice.springbootjwtauth.models.User;
import com.fabrice.springbootjwtauth.repositories.UserRepository;
import com.fabrice.springbootjwtauth.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    public User signup(SignupRequest signupRequest){
        User user = new User();
        user.setFName(signupRequest.getFName());
        user.setLName(signupRequest.getLName());
        user.setEmail(signupRequest.getEmail());
        user.setRole(Role.USER); // by default, all users created will have USER Role

        //hash user password
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        //save our user to db
      return userRepository.save(user);
    }
}
