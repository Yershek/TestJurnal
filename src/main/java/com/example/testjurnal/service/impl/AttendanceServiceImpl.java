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
                .orElseThrow(() -> new RuntimeException("Not Found"))
                .stream()
                .filter( attendance -> attendance.getStudent().getGroup().getId().equals(usersService.getCurrentUser().getGroupId()))
                .toList();
    }

    @Override
    public Attendance save(Attendance attendance) {
        return repository.save(attendance);
    }

    @Override
    public List<Attendance> saveAll(List<Attendance> attendances) {
        return repository.saveAll(attendances);
    }
}
