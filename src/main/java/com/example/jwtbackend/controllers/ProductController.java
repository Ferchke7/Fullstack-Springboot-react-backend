package com.example.jwtbackend.controllers;

import com.example.jwtbackend.entites.Product;
import com.example.jwtbackend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute Product productRequest) throws IOException {

        // Create a Product entity from the request data
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        // Handle the image upload and set the image URL
        if (file != null && !file.isEmpty()) {
            String imageUrl = productService.uploadImage(file);
            product.setImageUrl(imageUrl);
        }

        productService.saveProduct(product);
        System.out.println(product.getName() + " is : " + product.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }


        @GetMapping("/products")
        public ResponseEntity<List<Product>> returnProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
