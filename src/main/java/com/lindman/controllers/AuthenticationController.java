package com.lindman.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lindman.models.ApplicationUser;
import com.lindman.models.LoginResponseDTO;
import com.lindman.models.RegistrationDTO;
import com.lindman.services.AuthenticationService;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body, String email, String full_name){
        return authenticationService.registerUser(body.getUsername(), body.getPassword(), email,full_name );

    }
    
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}   
