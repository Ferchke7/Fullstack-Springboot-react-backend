package com.example.jwtbackend.entites;

import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;



@Table(name = "images_table")
public class Image {
    private String name;
    private Long price;

    @Lob
    private Blob coffeePicture;
}
