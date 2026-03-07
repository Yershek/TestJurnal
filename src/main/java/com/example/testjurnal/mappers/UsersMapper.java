package com.example.testjurnal.mappers;

import com.example.testjurnal.dto.request.UsersDtoRequest;
import com.example.testjurnal.dto.response.UsersDtoResponse;
import com.example.testjurnal.entity.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersMapper {

    public Users toEntity(UsersDtoRequest request){
        return Users.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .roles(List.of(request.getRole()))
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
