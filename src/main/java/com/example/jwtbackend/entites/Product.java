package com.example.jwtbackend.entites;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Blob;

@Data
@AllArgsConstructor
public class Product {
    String name;
    Double price;
    Blob productPicture;

}
