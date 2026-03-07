package com.example.testjurnal.mappers;

import com.example.testjurnal.dto.request.UsersDtoRequest;
import com.example.testjurnal.dto.response.UsersDtoResponse;
import com.example.testjurnal.entity.Users;
import com.example.testjurnal.repository.RolesRepository;
import com.example.testjurnal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersMapper {
    private final RoleService service;

    @Autowired
    public UsersMapper(RoleService service) {
        this.service = service;
    }

    public Users toEntity(UsersDtoRequest request){
        return Users.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .roles(List.of(service.getById(request.getRole())))
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
