package com.example.testjurnal.service.impl;

import com.example.testjurnal.entity.Student;
import com.example.testjurnal.repository.StudentRepository;
import com.example.testjurnal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudentByGroupId(Long groupId) {
        return studentRepository.findStudentsByGroupId(groupId)
                .orElse(List.of());
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.findById(student.getId())
                .map(exi -> studentRepository.save(student))
                .orElseThrow(() -> new RuntimeException("Not found Student by id"));
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public void delete(Long id) {
        studentRepository.delete(
                studentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Not found Student by id"))
        );
    }

}
