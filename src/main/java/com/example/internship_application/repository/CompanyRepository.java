package com.example.internship_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.internship_application.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {}
