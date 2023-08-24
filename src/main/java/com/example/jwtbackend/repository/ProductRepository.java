package com.example.jwtbackend.repository;

import com.example.jwtbackend.entites.Product;
import com.example.jwtbackend.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @Query("SELECT p.imageUrl FROM Product p")
    List<String> getAllByImageUrl();

    @Query("SELECT p FROM Product p")
    List<Product> getAllProducts();


    List<Product> getAllByUser(User user);
    List<Product> getByUserId(Long id);

}
