package com.example.internship_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.internship_application.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByStudentId(Long studentId);
}
