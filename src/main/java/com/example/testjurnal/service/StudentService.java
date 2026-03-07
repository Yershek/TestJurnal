package com.example.testjurnal.service;

import com.example.testjurnal.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentByGroupId(Long groupId);
    Student addStudent(Student student);
    Student update(Student student);
    Student getById(Long id);
    void  delete(Long id);
}
