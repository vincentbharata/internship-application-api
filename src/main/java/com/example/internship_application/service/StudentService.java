package com.example.internship_application.service;

import org.springframework.stereotype.Service;

import com.example.internship_application.model.Student;
import com.example.internship_application.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    
    private final StudentRepository studentRepo;

    public Student create(Student student) {
        return studentRepo.save(student);
    }
} //a
