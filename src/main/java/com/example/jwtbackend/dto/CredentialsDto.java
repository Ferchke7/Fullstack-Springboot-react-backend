package com.example.jwtbackend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialsDto {

    private String login;
    private char[] password;

}