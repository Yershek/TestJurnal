package com.example.testjurnal.mappers;

import com.example.testjurnal.dto.request.ScheduleDtoRequest;
import com.example.testjurnal.dto.response.ScheduleDtoResponse;
import com.example.testjurnal.entity.Schedule;
import com.example.testjurnal.entity.ScheduleLesson;
import com.example.testjurnal.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
    private final GroupService service;
    private final ScheduleLessonMapper mapper;

    @Autowired
    public ScheduleMapper(GroupService service, ScheduleLessonMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public Schedule toEntity(ScheduleDtoRequest scheduleDtoRequest){
        System.out.println(scheduleDtoRequest.toString());
        Schedule schedule = Schedule.builder()
                .group(service.getById(scheduleDtoRequest.getGroupId()))
                .scheduleDate(scheduleDtoRequest.getScheduleDate())
                .build();
        
        // Создаем ScheduleLesson с уже созданным Schedule
        var lessons = scheduleDtoRequest.getLessons().stream()
                .map(lessonRequest -> mapper.toEntity(lessonRequest, schedule))
                .toList();
        
        schedule.setLessons(lessons);
        return schedule;
    }

    public Schedule updateEntity(Schedule existingSchedule, ScheduleDtoRequest scheduleDtoRequest){
        System.out.println("Updating existing schedule with ID: " + existingSchedule.getId());
        System.out.println(scheduleDtoRequest.toString());
        
        // Обновляем группу и дату если нужно
        existingSchedule.setGroup(service.getById(scheduleDtoRequest.getGroupId()));
        existingSchedule.setScheduleDate(scheduleDtoRequest.getScheduleDate());
        
        // Создаем новую mutable коллекцию уроков вместо попытки очистить существующую
        var newLessons = new java.util.ArrayList<ScheduleLesson>();
        
        // Создаем новые уроки для существующего расписания
        var lessons = scheduleDtoRequest.getLessons().stream()
                .map(lessonRequest -> mapper.toEntity(lessonRequest, existingSchedule))
                .toList();
        
        newLessons.addAll(lessons);
        existingSchedule.setLessons(newLessons);
        return existingSchedule;
    }

    public ScheduleDtoResponse toResponse(Schedule schedule){
        return ScheduleDtoResponse.builder()
                .id(schedule.getId())
                .groupId(schedule.getGroup().getId())
                .scheduleDate(schedule.getScheduleDate())
                .lessons(schedule.getLessons().stream().map(mapper::toResponse).toList())
                .createdAt(schedule.getCreatedAt())
                .build();
    }
}
