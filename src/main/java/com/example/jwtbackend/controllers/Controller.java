package com.example.jwtbackend.controllers;

import com.example.jwtbackend.entites.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController

public class Controller {
    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages() {

        return ResponseEntity.ok(Arrays.asList("first", "second"));
    }

    @GetMapping("/products")
    public ResponseEntity<String> returnProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("Product 1", 10.99));
        products.add(new Product("Product 2", 25.49));
        products.add(new Product("Product 3", 5.99));
        products.add(new Product("Product 4", 14.75));
        products.add(new Product("Product 5", 8.99));
        products.add(new Product("Product 6", 19.99));
        products.add(new Product("Product 7", 12.00));
        products.add(new Product("Product 8", 7.25));
        products.add(new Product("Product 9", 16.50));
        products.add(new Product("Product 10", 22.99));
        System.out.println(products.toString());
        return ResponseEntity.ok(products.get(3).getName());
    }
}
