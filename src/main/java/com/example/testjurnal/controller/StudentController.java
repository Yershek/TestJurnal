package com.example.testjurnal.controller;

import com.example.testjurnal.dto.request.StudentDtoRequest;
import com.example.testjurnal.dto.response.StudentDtoResponse;
import com.example.testjurnal.dto.response.UsersDtoResponse;
import com.example.testjurnal.mappers.StudentMapper;
import com.example.testjurnal.mappers.UsersMapper;
import com.example.testjurnal.service.StudentService;
import com.example.testjurnal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final UsersService usersService;
    private final UsersMapper usersMapper;

    @Autowired
    public StudentController(
            StudentService studentService,
            StudentMapper studentMapper,
            UsersService usersService,
            UsersMapper usersMapper
    ) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.usersService = usersService;
        this.usersMapper = usersMapper;
    }

    @GetMapping("/get-all-starost")
    public ResponseEntity<List<UsersDtoResponse>> getAll(){
        return ResponseEntity.ok(
                usersService.getAll().stream()
                        .map(usersMapper::toResponse).toList()
        );
    }

    @GetMapping("/get-students-by-group-id/{groupId}")
    public ResponseEntity<List<StudentDtoResponse>> getStudentsByGroupId(
            @PathVariable  Long groupId){
        return ResponseEntity.ok(
                studentService
                        .getStudentByGroupId(groupId).stream()
                        .map(studentMapper::toResponse).toList()
        );
    }

    @PostMapping("/add")
    private ResponseEntity<StudentDtoResponse> save(
            @RequestBody StudentDtoRequest request){
        return ResponseEntity.ok(
                studentMapper.toResponse(
                        studentService.addStudent(
                                studentMapper.toEntity(request)
                        )
                )
        );
    }

    @PutMapping("/update")
    private ResponseEntity<StudentDtoResponse> update(
            @RequestBody StudentDtoRequest request){
        return ResponseEntity.ok(
                studentMapper.toResponse(
                        studentService.update(
                                studentMapper.toEntity(request)
                        )
                )
        );
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> delete(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.ok("Ok");
    }

}
