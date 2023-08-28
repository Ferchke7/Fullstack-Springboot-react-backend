package com.example.jwtbackend.controllers;

import com.example.jwtbackend.entites.Product;
import com.example.jwtbackend.entites.User;
import com.example.jwtbackend.service.ProductService;
import com.example.jwtbackend.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Date;
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
            @RequestParam String authenticatedUserLogin)
            throws IOException  {
        System.out.println(authenticatedUserLogin);
        User authenticatedUser = userService
                .getUserByLogin(authenticatedUserLogin);
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setUser(authenticatedUser);
        product.setCreatedDate(new Date());
        if (file != null && !file.isEmpty()) {
            String imageUrl = productService.uploadImage(file);
            product.setImageUrl(imageUrl);
        }
        productService.saveProduct(product);
        System.out.println(product.getName() + " is : " + product.getPrice() + " by " + authenticatedUser.getProducts().size());
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }



    @GetMapping("/products")
    public ResponseEntity<Page<Product>> returnProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Product> paginatedProducts = productService.getAllProducts(pageable);

        return ResponseEntity.ok(paginatedProducts);
    }

    @GetMapping("/products/count")
    public ResponseEntity<Long> getProductCount() {
        long totalCount = productService.getTotalProductCount();
        System.out.println(totalCount);
        return ResponseEntity.ok(totalCount);
    }

    @GetMapping("/myproducts/{id}")
    public ResponseEntity<List<Product>> returnUserProducts(@PathVariable Long id) {
       System.out.println(productService.getProductsByUser(id));
       return ResponseEntity.ok(productService.getProductsByUser(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        System.out.println(productService.deleteProductById(id));
        System.out.println("deleted Product id of " + id);
        return ResponseEntity.ok("deleted Product id of " + id);
    }


}
