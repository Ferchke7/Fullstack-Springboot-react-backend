package com.example.jwtbackend.controllers;

import com.cloudinary.Cloudinary;
import com.example.jwtbackend.entites.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController

public class Controller {

    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages() {

        return ResponseEntity.ok(Arrays.asList("first", "second"));
    }



    //TODO create new endpoint

//    @PostMapping("/create")
//    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
//
//    }

//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> returnProducts() {
//        System.out.println(products.toString());
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/products/{id}")
//    public ResponseEntity<Product> productsByName(@PathVariable String id){
//        String tempProduct = "Product " + id;
//
//        for (Product product : products) {
//            if (product.getName().equals(tempProduct)) {
//                System.out.println(product);
//                return ResponseEntity.ok(product);
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }
}
