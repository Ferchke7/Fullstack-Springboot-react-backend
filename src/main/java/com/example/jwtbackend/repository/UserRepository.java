package com.example.jwtbackend.repository;


import com.example.jwtbackend.entites.Product;
import com.example.jwtbackend.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
    Optional<User> findByFirstName(String username);
    Optional<User> getByFirstName(String name);

}