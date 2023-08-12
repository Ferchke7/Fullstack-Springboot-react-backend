package com.example.jwtbackend.entites;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Set<Product> listOfProducts;

    Double overallPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
