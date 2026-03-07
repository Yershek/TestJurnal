package com.example.testjurnal.dto.request;

import lombok.*;

import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDtoRequest {
    private String title;
    private String teacherName;
    private String classroom;
    private Integer lessonNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long groupId;
}
