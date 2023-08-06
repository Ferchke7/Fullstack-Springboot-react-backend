package com.example.jwtbackend.controllers;

//import config.UserAuthenticationProvider;
import com.example.jwtbackend.config.UserAuthenticationProvider;
import com.example.jwtbackend.dto.CredentialsDto;
import com.example.jwtbackend.dto.SignUpDto;
import com.example.jwtbackend.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jwtbackend.service.UserService;

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
        System.out.println("Entered as : " + credentialsDto.getLogin());
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/test")
    public ResponseEntity<CredentialsDto> test(@RequestBody @Valid CredentialsDto credentialsDto) {
        System.out.println("created : " + credentialsDto.getLogin() + " " + credentialsDto.getPassword());
        return ResponseEntity.ok(credentialsDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getLogin()));
        System.out.println("Tokens for: " + user.getLogin() + " " + user.getLastName());
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}
