package com.example.jwtbackend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {
    @GetMapping("/messages")
    public ResponseEntity<List<String>> message() {
        return ResponseEntity.ok(Arrays.asList("first","second"));
    }
}
