package com.example.testjurnal.dto.response;

import com.example.testjurnal.entity.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDtoResponse {
    private Long id;
    private String username;
    private List<UserRole> role;
    private String groupId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
