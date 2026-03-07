package com.example.testjurnal.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDtoResponse {
    private Long id;
    private String name;
    private String facultyName;
    private Integer courseNumber;
    private LocalDateTime createdAt;
}
