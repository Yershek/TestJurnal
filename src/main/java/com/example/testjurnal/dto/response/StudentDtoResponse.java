package com.example.testjurnal.dto.response;

import com.example.testjurnal.entity.Group;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoResponse {
    private Long id;
    private String fullName;
    private Long groupId;
    private String studentNumber;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}
