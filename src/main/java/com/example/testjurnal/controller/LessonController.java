package com.example.testjurnal.controller;

import com.example.testjurnal.dto.request.LessonDtoRequest;
import com.example.testjurnal.dto.response.LessonDtoResponse;
import com.example.testjurnal.entity.Lesson;
import com.example.testjurnal.mappers.LessonMapper;
import com.example.testjurnal.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {
    private final LessonService lessonService;
    private final LessonMapper lessonMapper;

    @Autowired
    public LessonController(
            LessonService lessonService,
            LessonMapper lessonMapper
    ) {
        this.lessonService = lessonService;
        this.lessonMapper = lessonMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<LessonDtoResponse> save(@RequestBody LessonDtoRequest request){
        return ResponseEntity.ok(
                lessonMapper.toResponse(
                        lessonService.save(lessonMapper.toEntity(request))
                )
        );
    }

    @GetMapping("/get-by-group-and-date/{groupId}/{date}")
    public ResponseEntity<List<LessonDtoResponse>> getLessonsByGroupAndDate(
            @PathVariable Long groupId,
            @PathVariable String date
    ){
        return ResponseEntity.ok(
                lessonService.getLessonsByGroupAndDate(groupId, date).stream()
                        .map(lessonMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/get-by-group/{groupId}")
    public ResponseEntity<List<LessonDtoResponse>> getLessonsByGroup(
            @PathVariable Long groupId
    ){
        return ResponseEntity.ok(
                lessonService.getLessonsByGroup(groupId).stream()
                        .map(lessonMapper::toResponse)
                        .toList()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        lessonService.delete(id);
        return ResponseEntity.ok("Ok");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LessonDtoResponse> update(
            @PathVariable Long id,
            @RequestBody LessonDtoRequest request
    ){
        // Устанавливаем ID из path variable
        Lesson lesson = lessonMapper.toEntity(request);
        lesson.setId(id);
        
        return ResponseEntity.ok(
                lessonMapper.toResponse(
                        lessonService.update(lesson)
                )
        );
    }
}
