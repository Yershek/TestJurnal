package com.example.testjurnal.service.impl;

import com.example.testjurnal.entity.ScheduleLesson;
import com.example.testjurnal.repository.ScheduleLessonRepository;
import com.example.testjurnal.service.ScheduleLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleLessonServiceImpl implements ScheduleLessonService {
    private final ScheduleLessonRepository scheduleLessonRepository;

    @Autowired
    public ScheduleLessonServiceImpl(ScheduleLessonRepository scheduleLessonRepository) {
        this.scheduleLessonRepository = scheduleLessonRepository;
    }

    @Override
    public ScheduleLesson save(ScheduleLesson scheduleLesson) {
        return scheduleLessonRepository.save(scheduleLesson);
    }

    @Override
    public ScheduleLesson getById(Long id) {
        return scheduleLessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
