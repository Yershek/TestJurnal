package com.example.testjurnal.controller;

import com.example.testjurnal.dto.request.AttendanceDtoRequest;
import com.example.testjurnal.dto.response.AttendanceDtoResponse;
import com.example.testjurnal.mappers.AttendanceMapper;
import com.example.testjurnal.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService service;
    private final AttendanceMapper mapper;

    @Autowired
    public AttendanceController(AttendanceService service, AttendanceMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/get-attendance-by-date-and-lesson-id/{date}/{lessonId}")
    public ResponseEntity<List<AttendanceDtoResponse>> getAttendance(
            @PathVariable LocalDate date,
            @PathVariable Long lessonId
    ){
        return ResponseEntity.ok(
                service.getAttendanceByDateAndLessonId(date, lessonId)
                        .stream().map(mapper::toResponse).toList()
        );
    }

    @PostMapping("/save-attendance")
    public ResponseEntity<AttendanceDtoResponse> saveAtt(@RequestBody AttendanceDtoRequest request){
        return ResponseEntity.ok(
                mapper.toResponse(
                        service.save(mapper.toEntity(request))
                )
        );
    }

    @PostMapping("/save-attendance-all")
    public ResponseEntity<List<AttendanceDtoResponse>> saveAll(
            @RequestBody List<AttendanceDtoRequest> requests
    ){
        return ResponseEntity.ok(
                service.saveAll(requests.stream().map(mapper::toEntity).toList())
                        .stream().map(mapper::toResponse).toList()
        );
    }
}
