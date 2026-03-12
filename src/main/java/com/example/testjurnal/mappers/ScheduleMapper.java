package com.example.testjurnal.mappers;

import com.example.testjurnal.dto.request.ScheduleDtoRequest;
import com.example.testjurnal.dto.response.ScheduleDtoResponse;
import com.example.testjurnal.entity.Schedule;
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
        return Schedule.builder()
                .group(service.getById(scheduleDtoRequest.getGroupId()))
                .scheduleDate(scheduleDtoRequest.getScheduleDate())
                .lessons(scheduleDtoRequest.getLessons().stream().map(mapper::toEntity).toList())
                .build();
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
