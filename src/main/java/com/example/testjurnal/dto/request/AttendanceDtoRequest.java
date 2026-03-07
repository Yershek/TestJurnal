package com.example.testjurnal.dto.request;

import com.example.testjurnal.enums.AttendanceStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDtoRequest {
    private Long studentId;
    private Long scheduleLessonId;
    private AttendanceStatus status;
    private LocalDate attendanceDate;
    private LocalDateTime markedAt;
    private String notes;
}
