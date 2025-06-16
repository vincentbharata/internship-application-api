package com.example.internship_application.service;

import com.example.internship_application.dto.*;
import com.example.internship_application.model.*;
import com.example.internship_application.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository appRepo;
    private final StudentRepository studentRepo;
    private final CompanyRepository companyRepo;

    private final List<String> allowedStatuses = List.of("PENDING", "ACCEPTED", "REJECTED");

    public ApplicationResponse apply(ApplicationRequest request) {
        Student student = studentRepo.findById(request.studentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Company company = companyRepo.findById(request.companyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        Application application = new Application();
        application.setStudent(student);
        application.setCompany(company);
        application.setResumeLink(request.resumeLink());
        application.setStatus("PENDING");

        appRepo.save(application);
        return mapToResponse(application);
    }

    public List<ApplicationResponse> findAll() {
        return appRepo.findAll().stream().map(this::mapToResponse).toList();
    }

    public ApplicationResponse findById(Long id) {
        return appRepo.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
    }

    public ApplicationResponse updateStatus(Long id, String status) {
        status = status.toUpperCase();
        if (!allowedStatuses.contains(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        Application app = appRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        app.setStatus(status);
        appRepo.save(app);
        return mapToResponse(app);
    }

    public void delete(Long id) {
        appRepo.deleteById(id);
    }

    public List<ApplicationResponse> findByStudentId(Long studentId) {
        return appRepo.findByStudentId(studentId).stream()
                .map(this::mapToResponse).toList();
    }

    private ApplicationResponse mapToResponse(Application app) {
        return new ApplicationResponse(
                app.getId(),
                app.getStudent().getName(),
                app.getCompany().getName(),
                app.getResumeLink(),
                app.getStatus()
        );
    }
}
