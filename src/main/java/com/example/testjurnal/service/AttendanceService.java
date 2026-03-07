package com.example.testjurnal.service;

import com.example.testjurnal.entity.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    List<Attendance> getAttendanceByDateAndLessonId(LocalDate date, Long lessonId);
    Attendance save(Attendance attendance);
    List<Attendance> saveAll(List<Attendance> attendances);
}
