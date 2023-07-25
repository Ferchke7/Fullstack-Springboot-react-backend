package com.example.jwtbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class JwtBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtBackendApplication.class, args);
    }

}
