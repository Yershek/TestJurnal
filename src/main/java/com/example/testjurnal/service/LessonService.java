package com.example.testjurnal.service;

import com.example.testjurnal.entity.Lesson;

public interface LessonService {
    Lesson save(Lesson lesson);
    Lesson getById(Long id);
}
