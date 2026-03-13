package com.example.testjurnal.mappers;

import com.example.testjurnal.dto.request.LessonDtoRequest;
import com.example.testjurnal.dto.response.LessonDtoResponse;
import com.example.testjurnal.entity.Lesson;
import com.example.testjurnal.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class LessonMapper {
    private final GroupService service;

    @Autowired
    public LessonMapper(GroupService service) {
        this.service = service;
    }

    public Lesson toEntity(LessonDtoRequest request){
        return Lesson.builder()
                .title(request.getTitle())
                .teacherName(request.getTeacherName())
                .classroom(request.getClassroom())
                .lessonNumber(request.getLessonNumber())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .group(service.getById(request.getGroupId()))
                .build();
    }

    public LessonDtoResponse toResponse(Lesson lesson){
        return LessonDtoResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .teacherName(lesson.getTeacherName())
                .classroom(lesson.getClassroom())
                .lessonNumber(lesson.getLessonNumber())
                .startTime(lesson.getStartTime())
                .endTime(lesson.getEndTime())
                .groupId(lesson.getGroup() != null ? lesson.getGroup().getId() : null)
                .createdAt(lesson.getCreatedAt())
                .build();
    }
}
