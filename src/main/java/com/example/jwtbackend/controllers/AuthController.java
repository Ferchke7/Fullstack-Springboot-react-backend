package com.example.jwtbackend.controllers;

//import config.UserAuthenticationProvider;
import com.example.jwtbackend.config.UserAuthenticationProvider;
import com.example.jwtbackend.dto.CredentialsDto;
import com.example.jwtbackend.dto.SignUpDto;
import com.example.jwtbackend.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.jwtbackend.service.UserService;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URI;


@RequiredArgsConstructor
@RestController

public class AuthController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
        System.out.println("Entered as for DTO: " + userDto.getLogin() + " as credentials: " + credentialsDto.getLogin());
        return ResponseEntity.ok(userDto);
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getLogin()));
        System.out.println("USER for: " + user.getLogin() + " " + " for token: " + createdUser.getLogin());
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}