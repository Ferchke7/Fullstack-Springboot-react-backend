package com.example.jwtbackend.service;

import com.example.jwtbackend.dto.CredentialsDto;
import com.example.jwtbackend.dto.SignUpDto;
import com.example.jwtbackend.dto.UserDto;


import com.example.jwtbackend.entites.Roles;
import com.example.jwtbackend.entites.User;
import com.example.jwtbackend.exception.AppException;
import com.example.jwtbackend.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.jwtbackend.repository.UserRepository;

import java.nio.CharBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        System.out.println("EMAIL AND LOGIN: " + credentialsDto.getLogin());
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());
        Optional<User> optionalUserEmail = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }
        if (optionalUserEmail.isPresent()) {
            throw new AppException("Email already has been taken", HttpStatus.BAD_REQUEST);
        }
        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
        user.setRoles(Collections.singleton(Roles.USER));
        User savedUser = userRepository.save(user);
        //{TODO} change it to role based
//        Role defaultRole = roleRepository.findByName("ROLE_USER");
//        user.setRoles(Collections.singleton(defaultRole));
        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }
    public User getUserByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return user;
    }


    public List<User> getAllUSer() {
       return userRepository.findAll();
    }

    @Transactional
    public void updateUserRole(String userLogin, String newRole) {
        User user = userRepository.getUsersByLogin(userLogin);
        user.setRoles(Collections.singleton(Roles.valueOf(newRole)));
    }



}