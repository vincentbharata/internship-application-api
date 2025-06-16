package com.example.internship_application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ApplicationRequest {
    @NotNull
    private Long studentId;
    @NotNull
    private Long companyId;
    @Pattern(regexp = "https://.+", message = "Resume must be a valid URL")
    private String resumeLink;
    @NotBlank
    private String status;
    private Long id;
    private String studentName;
    private String companyName;
}
