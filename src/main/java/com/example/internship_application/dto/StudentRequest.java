package com.example.internship_application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class StudentRequest (
    @NotBlank String name,
    @Email @NotBlank String email
){}
