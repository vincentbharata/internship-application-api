package com.example.internship_application.dto;

public record ApplicationResponse(
    Long id,
    String studentName,
    String companyName,
    String resumeLink,
    String status
) {}
