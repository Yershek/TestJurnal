package com.example.testjurnal.service.impl;

import com.example.testjurnal.entity.UserRole;
import com.example.testjurnal.repository.RolesRepository;
import com.example.testjurnal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    public final RolesRepository repository;

    @Autowired
    public RoleServiceImpl(RolesRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserRole getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
