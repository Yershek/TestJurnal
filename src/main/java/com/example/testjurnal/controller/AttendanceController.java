package com.example.testjurnal.controller;

import com.example.testjurnal.dto.request.AttendanceDtoRequest;
import com.example.testjurnal.dto.response.AttendanceDtoResponse;
import com.example.testjurnal.entity.Attendance;
import com.example.testjurnal.enums.AttendanceStatus;
import com.example.testjurnal.mappers.AttendanceMapper;
import com.example.testjurnal.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            @PathVariable String date,
            @PathVariable Long lessonId
    ){
        try {
            // Парсим дату из ISO формата
            LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return ResponseEntity.ok(
                    service.getAttendanceByDateAndLessonId(parsedDate, lessonId)
                            .stream().map(mapper::toResponse).toList()
            );
        } catch (DateTimeParseException e) {
            // Если не удалось спарсить как LocalDate, пробуем ZonedDateTime
            try {
                ZonedDateTime zdt = ZonedDateTime.parse(date);
                return ResponseEntity.ok(
                        service.getAttendanceByDateAndLessonId(zdt.toLocalDate(), lessonId)
                                .stream().map(mapper::toResponse).toList()
                );
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Invalid date format: " + date + ". Expected ISO date format (YYYY-MM-DD) or ISO datetime format.", ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving attendance: " + e.getMessage(), e);
        }
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

    @PostMapping("/save-attendance-batch")
    public ResponseEntity<List<AttendanceDtoResponse>> saveBatch(
            @RequestBody Map<String, Object> payload
    ){
        try {
            String dateStr = (String) payload.get("date");
            String lessonId = (String) payload.get("lessonId");
            List<Map<String, Object>> records = (List<Map<String, Object>>) payload.get("records");
            
            // Парсим дату
            LocalDate attendanceDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
            Long scheduleLessonId = Long.parseLong(lessonId);
            
            // Получаем существующие записи посещаемости для этой даты и урока
            List<Attendance> existingAttendances = service.getAttendanceByDateAndLessonId(attendanceDate, scheduleLessonId);
            
            // Создаем мапу существующих записей (studentId -> Attendance)
            Map<Long, Attendance> existingMap = existingAttendances.stream()
                    .collect(java.util.stream.Collectors.toMap(
                            att -> att.getStudent().getId(),
                            att -> att
                    ));
            
            // Обновляем или создаем записи
            List<Attendance> updatedAttendances = new ArrayList<>();
            
            for (Map<String, Object> record : records) {
                // Получаем studentId
                Object studentIdObj = record.get("studentId");
                Long studentId = null;
                if (studentIdObj instanceof Number) {
                    studentId = ((Number) studentIdObj).longValue();
                } else if (studentIdObj instanceof String) {
                    studentId = Long.parseLong((String) studentIdObj);
                }
                
                // Получаем статус
                String statusStr = (String) record.get("status");
                AttendanceStatus status = null;
                for (AttendanceStatus as : AttendanceStatus.values()) {
                    if (as.getValue().equals(statusStr)) {
                        status = as;
                        break;
                    }
                }
                if (status == null) {
                    throw new IllegalArgumentException("Invalid attendance status: " + statusStr);
                }
                
                // Получаем notes если есть
                Object notes = record.get("notes");
                String notesStr = notes != null ? (String) notes : null;
                
                // Проверяем, существует ли запись
                Attendance existingAttendance = existingMap.get(studentId);
                
                if (existingAttendance != null) {
                    // Обновляем существующую запись
                    existingAttendance.setStatus(status);
                    existingAttendance.setNotes(notesStr);
                    existingAttendance.setMarkedAt(java.time.LocalDateTime.now());
                    updatedAttendances.add(existingAttendance);
                } else {
                    // Создаем новую запись
                    AttendanceDtoRequest request = AttendanceDtoRequest.builder()
                            .studentId(studentId)
                            .scheduleLessonId(scheduleLessonId)
                            .status(status)
                            .attendanceDate(attendanceDate)
                            .markedAt(java.time.LocalDateTime.now())
                            .notes(notesStr)
                            .build();
                    
                    Attendance newAttendance = mapper.toEntity(request);
                    updatedAttendances.add(newAttendance);
                }
            }
            
            // Сохраняем все записи
            List<Attendance> savedAttendances = service.saveAll(updatedAttendances);
            
            return ResponseEntity.ok(
                    savedAttendances.stream()
                            .map(mapper::toResponse)
                            .toList()
            );
            
        } catch (Exception e) {
            throw new RuntimeException("Error processing attendance batch: " + e.getMessage(), e);
        }
    }
}
