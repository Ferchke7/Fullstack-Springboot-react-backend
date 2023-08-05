package com.example.jwtbackend.mapper;


import com.example.jwtbackend.dto.SignUpDto;
import com.example.jwtbackend.dto.UserDto;
import com.example.jwtbackend.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}