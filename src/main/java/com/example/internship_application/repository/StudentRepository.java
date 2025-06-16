package com.example.internship_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.internship_application.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {}
