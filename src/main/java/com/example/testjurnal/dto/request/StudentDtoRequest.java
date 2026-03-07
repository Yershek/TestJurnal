package com.example.testjurnal.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoRequest {
    private String fullName;
    private Long groupId;
    private String studentNumber;
    private String email;
    private String phone;
}
