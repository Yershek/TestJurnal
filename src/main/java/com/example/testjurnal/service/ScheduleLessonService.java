package com.example.testjurnal.service;

import com.example.testjurnal.entity.ScheduleLesson;

public interface ScheduleLessonService {
    ScheduleLesson save(ScheduleLesson scheduleLesson);
    ScheduleLesson getById(Long id);
}
