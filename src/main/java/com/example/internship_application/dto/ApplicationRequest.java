package com.example.internship_application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ApplicationRequest(
    @NotNull Long studentId,
    @NotNull Long companyId,
    @Pattern(regexp = "https://.+", message = "Resume must be a valid URL") String resumeLink
) {}
