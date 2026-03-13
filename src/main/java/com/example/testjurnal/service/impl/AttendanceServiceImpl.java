package com.example.testjurnal.service.impl;

import com.example.testjurnal.entity.Attendance;
import com.example.testjurnal.repository.AttendanceRepository;
import com.example.testjurnal.service.AttendanceService;
import com.example.testjurnal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository repository;
    private final UsersService usersService;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository repository, UsersService usersService) {
        this.repository = repository;
        this.usersService = usersService;
    }

    public List<Attendance> getAttendanceByDateAndLessonId(LocalDate date, Long lessonId) {
        return repository.findAttendancesByAttendanceDateAndScheduleLessonId(date, lessonId)
                .orElse(List.of()); // Возвращаем пустой список, если ничего не найдено
    }

    @Override
    public Attendance save(Attendance attendance) {
        Attendance exam = repository.findAttendanceByStudentIdAndScheduleLessonId(
                attendance.getStudent().getId(), attendance.getScheduleLesson().getId()
        );
        if (exam == null) {
            return repository.save(attendance);
        }else {
            exam.setStatus(attendance.getStatus());
            return repository.save(exam);
        }
    }

    @Override
    public List<Attendance> saveAll(List<Attendance> attendances) {
        return repository.saveAll(attendances.stream()
                .map(attendance -> {
                    Attendance existing = repository.findAttendanceByStudentIdAndScheduleLessonId(
                            attendance.getStudent().getId(),
                            attendance.getScheduleLesson().getId()
                    );
                    if (existing == null) {
                        return attendance;
                    } else {
                        existing.setStatus(attendance.getStatus());
                        return existing;
                    }
                }).toList());
    }
}
