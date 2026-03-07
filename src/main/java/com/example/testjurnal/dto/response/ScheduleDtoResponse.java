package com.example.testjurnal.dto.response;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDtoResponse {
    private Long id;
    private Long groupId;
    private LocalDate scheduleDate;
    private List<ScheduleLessonDtoResponse> lessons;
    private LocalDateTime createdAt;
}
