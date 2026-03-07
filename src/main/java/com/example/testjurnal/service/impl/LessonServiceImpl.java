package com.example.testjurnal.service.impl;

import com.example.testjurnal.entity.Lesson;
import com.example.testjurnal.repository.LessonRepository;
import com.example.testjurnal.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson getById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
