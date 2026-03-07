package com.example.testjurnal.service;

import com.example.testjurnal.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {
    Users getCurrentUser();
    String auth(String name, String password);
    Users register(Users users);
    List<Users> getAll();
}
