package com.example.testjurnal.dto.response;

import com.example.testjurnal.entity.ScheduleLesson;
import com.example.testjurnal.enums.AttendanceStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDtoResponse {
    private Long id;
    private Long studentId;
    private Long scheduleLessonId;
    private AttendanceStatus status;
    private LocalDate attendanceDate;
    private LocalDateTime markedAt;
    private String notes;
    private LocalDateTime createdAt;
}
