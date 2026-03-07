package com.example.testjurnal.mappers;

import com.example.testjurnal.dto.request.ScheduleLessonDtoRequest;
import com.example.testjurnal.dto.response.ScheduleLessonDtoResponse;
import com.example.testjurnal.entity.ScheduleLesson;
import com.example.testjurnal.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleLessonMapper {
    private final ScheduleService service;
    private final LessonMapper lessonMapper;

    @Autowired
    public ScheduleLessonMapper(ScheduleService service, LessonMapper lessonMapper) {
        this.service = service;
        this.lessonMapper = lessonMapper;
    }

    public ScheduleLesson toEntity(ScheduleLessonDtoRequest request){
        return ScheduleLesson.builder()
                .schedule(service.getById(request.getScheduleId()))
                .lesson(lessonMapper.toEntity(request.getLesson()))
                .lessonOrder(request.getLessonOrder())
                .build();
    }

    public ScheduleLessonDtoResponse toResponse(ScheduleLesson lesson){
        return ScheduleLessonDtoResponse.builder()
                .id(lesson.getId())
                .scheduleId(lesson.getSchedule().getId())
                .lesson(lessonMapper.toResponse(lesson.getLesson()))
                .lessonOrder(lesson.getLessonOrder())
                .build();
    }
}
