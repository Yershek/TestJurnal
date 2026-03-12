package com.example.testjurnal.service;

import com.example.testjurnal.entity.UserRole;

public interface RoleService {
    UserRole getById(Long id);
    UserRole getByName(String name);
}
