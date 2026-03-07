package com.example.testjurnal.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleLessonDtoResponse {
    private Long id;
    private Long scheduleId;
    private LessonDtoResponse lesson;
    private Integer lessonOrder;
}
