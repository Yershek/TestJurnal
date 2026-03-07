package com.example.testjurnal.mappers;

import com.example.testjurnal.dto.request.AttendanceDtoRequest;
import com.example.testjurnal.dto.response.AttendanceDtoResponse;
import com.example.testjurnal.entity.Attendance;
import com.example.testjurnal.service.ScheduleLessonService;
import com.example.testjurnal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {
    private final StudentService service;
    private final ScheduleLessonService scheduleLessonService;

    @Autowired
    public AttendanceMapper(StudentService service, ScheduleLessonService scheduleLessonService) {
        this.service = service;
        this.scheduleLessonService = scheduleLessonService;
    }

    public Attendance toEntity(AttendanceDtoRequest request){
        return Attendance.builder()
                .student(service.getById(request.getStudentId()))
                .scheduleLesson(scheduleLessonService.getById(request.getScheduleLessonId()))
                .status(request.getStatus())
                .attendanceDate(request.getAttendanceDate())
                .markedAt(request.getMarkedAt())
                .notes(request.getNotes())
                .build();
    }

    public AttendanceDtoResponse toResponse(Attendance attendance){
        return AttendanceDtoResponse.builder()
                .id(attendance.getId())
                .studentId(attendance.getStudent().getId())
                .scheduleLessonId(attendance.getScheduleLesson().getId())
                .status(attendance.getStatus())
                .attendanceDate(attendance.getAttendanceDate())
                .markedAt(attendance.getMarkedAt())
                .notes(attendance.getNotes())
                .createdAt(attendance.getCreatedAt())
                .build();
    }
}
