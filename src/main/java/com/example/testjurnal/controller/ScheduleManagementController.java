package com.example.testjurnal.controller;

import com.example.testjurnal.dto.request.ScheduleDtoRequest;
import com.example.testjurnal.dto.response.ScheduleDtoResponse;
import com.example.testjurnal.mappers.ScheduleMapper;
import com.example.testjurnal.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleManagementController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleManagementController(
            ScheduleService scheduleService,
            ScheduleMapper scheduleMapper
    ) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @GetMapping("/get-schedule-by-date-or-groupId/{date}/{groupId}")
    public ResponseEntity<ScheduleDtoResponse> getScheduleByDateOrGroupId(
            @PathVariable LocalDate date,
            @PathVariable Long groupId
    ){
        return ResponseEntity.ok(
                scheduleMapper.toResponse(
                        scheduleService.getScheduleByDateAndGroup(groupId, date)
                )
        );
    }

    @PostMapping("/save")
    public ResponseEntity<ScheduleDtoResponse> save(@RequestBody ScheduleDtoRequest request){
        return ResponseEntity.ok(
                scheduleMapper.toResponse(
                        scheduleService.save(scheduleMapper.toEntity(request))
                )
        );
    }
}
