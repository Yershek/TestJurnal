package com.example.testjurnal.service;

import com.example.testjurnal.entity.Lesson;

import java.util.List;

public interface LessonService {
    Lesson save(Lesson lesson);
    Lesson getById(Long id);
    List<Lesson> getLessonsByGroupAndDate(Long groupId, String date);
    List<Lesson> getLessonsByGroup(Long groupId);
    Lesson update(Lesson lesson);
    void delete(Long id);
}
