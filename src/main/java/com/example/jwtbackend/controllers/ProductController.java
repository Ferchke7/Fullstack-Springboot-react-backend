package com.example.jwtbackend.controllers;

import com.example.jwtbackend.entites.Product;
import com.example.jwtbackend.entites.User;
import com.example.jwtbackend.service.ProductService;
import com.example.jwtbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute Product productRequest,
            @RequestParam String authenticatedUserLogin) throws IOException  {
        System.out.println(authenticatedUserLogin);
        User authenticatedUser = userService.getUserByLogin(authenticatedUserLogin);

        // Create a Product entity from the request data
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setUser(authenticatedUser);
        // Handle the image upload and set the image URL
        if (file != null && !file.isEmpty()) {
            String imageUrl = productService.uploadImage(file);
            product.setImageUrl(imageUrl);
        }

        productService.saveProduct(product);
        System.out.println(product.getName() + " is : " + product.getPrice() + " by " + authenticatedUser.getProducts().size());
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }


        @GetMapping("/products")
        public ResponseEntity<List<Product>> returnProducts() {
            System.out.println(productService.getAllProducts());
        return ResponseEntity.ok(productService.getAllProducts());
    }

        @GetMapping("/myproducts/{id}")
        public ResponseEntity<List<Product>> returnUserProducts(@PathVariable Long id) {
            System.out.println(productService.getProductsByUser(id));
            return ResponseEntity.ok(productService.getProductsByUser(id));
        }



}
