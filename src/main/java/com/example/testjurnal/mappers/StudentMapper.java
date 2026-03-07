package com.example.testjurnal.mappers;

import com.example.testjurnal.dto.request.StudentDtoRequest;
import com.example.testjurnal.dto.response.StudentDtoResponse;
import com.example.testjurnal.entity.Student;
import com.example.testjurnal.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    private final GroupService service;

    @Autowired
    public StudentMapper(GroupService service) {
        this.service = service;
    }

    public Student toEntity(StudentDtoRequest request){
        return Student.builder()
                .fullName(request.getFullName())
                .group(service.getById(request.getGroupId()))
                .studentNumber(request.getStudentNumber())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }

    public StudentDtoResponse toResponse(Student student){
        return StudentDtoResponse.builder()
                .id(student.getId())
                .fullName(student.getFullName())
                .groupId(student.getGroup().getId())
                .studentNumber(student.getStudentNumber())
                .email(student.getEmail())
                .phone(student.getPhone())
                .createdAt(student.getCreatedAt())
                .build();
    }
}
