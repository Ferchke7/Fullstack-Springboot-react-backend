package com.example.jwtbackend.service;

import com.example.jwtbackend.entites.Role;

public interface RoleService {
    Role findByName(String name);
}
