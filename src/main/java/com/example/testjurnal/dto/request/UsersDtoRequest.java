package com.example.testjurnal.dto.request;

import com.example.testjurnal.entity.UserRole;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDtoRequest {
    private String username;
    private String password;
    private UserRole role;
    private String groupId;
}
