package com.example.testjurnal.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleLessonDtoRequest {
    private Long scheduleId;
    private LessonDtoRequest lesson;
    private Integer lessonOrder;
}
