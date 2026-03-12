package com.example.testjurnal.mappers;

import com.example.testjurnal.dto.request.UsersDtoRequest;
import com.example.testjurnal.dto.response.UsersDtoResponse;
import com.example.testjurnal.entity.Users;
import com.example.testjurnal.repository.RolesRepository;
import com.example.testjurnal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersMapper {
    private final RoleService service;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersMapper(RoleService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    public Users toEntity(UsersDtoRequest request){
        return Users.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(service.getByName(request.getRole().substring(5))))
                .groupId(request.getGroupId())
                .build();
    }

    public UsersDtoResponse toResponse(Users users){
        return UsersDtoResponse.builder()
                .id(users.getId())
                .username(users.getUsername())
                .role(users.getRoles())
                .groupId(users.getGroupId())
                .createdAt(users.getCreatedAt())
                .updatedAt(users.getUpdatedAt())
                .build();
    }
}
