package com.example.testjurnal.service.impl;

import com.example.testjurnal.entity.Lesson;
import com.example.testjurnal.repository.LessonRepository;
import com.example.testjurnal.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public List<Lesson> getLessonsByGroupAndDate(Long groupId, String date) {
        // Преобразуем строку даты в LocalDate если нужно
        LocalDate localDate = LocalDate.parse(date);
        return lessonRepository.findLessonsByGroupIdAndDate(groupId, localDate)
                .orElse(List.of());
    }

    @Override
    public List<Lesson> getLessonsByGroup(Long groupId) {
        return lessonRepository.findLessonsByGroupId(groupId)
                .orElse(List.of());
    }

    @Override
    public Lesson update(Lesson lesson) {
        return lessonRepository.findById(lesson.getId())
                .map(existing -> lessonRepository.save(lesson))
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    @Override
    public void delete(Long id) {
        lessonRepository.delete(
                lessonRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Lesson not found"))
        );
    }
}
