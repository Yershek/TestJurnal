package com.example.testjurnal.service;

import com.example.testjurnal.entity.Schedule;

import java.time.LocalDate;

public interface ScheduleService {
    Schedule save(Schedule schedule);
    Schedule getScheduleByDateAndGroup(Long groupId, LocalDate time);
    Schedule getById(Long id);
}
