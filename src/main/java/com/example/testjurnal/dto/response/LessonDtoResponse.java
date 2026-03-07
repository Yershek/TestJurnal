package com.example.testjurnal.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDtoResponse {
    private Long id;
    private String title;
    private String teacherName;
    private String classroom;
    private Integer lessonNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long groupId;
    private LocalDateTime createdAt;
}
