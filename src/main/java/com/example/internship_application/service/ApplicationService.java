package com.example.internship_application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.internship_application.dto.ApplicationRequest;
import com.example.internship_application.model.Application;
import com.example.internship_application.model.Company;
import com.example.internship_application.model.Student;
import com.example.internship_application.repository.ApplicationRepository;
import com.example.internship_application.repository.CompanyRepository;
import com.example.internship_application.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository appRepo;
    private final StudentRepository studentRepo;
    private final CompanyRepository companyRepo;

    private final List<String> allowedStatuses = List.of("PENDING", "ACCEPTED", "REJECTED");

    public ApplicationRequest apply(ApplicationRequest request) {
        Student student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Company company = companyRepo.findById(request.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        Application application = new Application();
        application.setStudent(student);
        application.setCompany(company);
        application.setResumeLink(request.getResumeLink());
        application.setStatus("PENDING");

        appRepo.save(application);
        return mapToDto(application);
    }

    public List<ApplicationRequest> findAll() {
        return appRepo.findAll().stream().map(this::mapToDto).toList();
    }

    public ApplicationRequest findById(Long id) {
        return appRepo.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
    }

    public ApplicationRequest updateStatus(Long id, String status) {
        status = status.toUpperCase();
        if (!allowedStatuses.contains(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        Application app = appRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        app.setStatus(status);
        appRepo.save(app);
        return mapToDto(app);
    }

    public void delete(Long id) {
        appRepo.deleteById(id);
    }

    public List<ApplicationRequest> findByStudentId(Long studentId) {
        return appRepo.findByStudentId(studentId).stream()
                .map(this::mapToDto).toList();
    }

    private ApplicationRequest mapToDto(Application app) {
        ApplicationRequest dto = new ApplicationRequest();
        dto.setId(app.getId());
        dto.setStudentId(app.getStudent().getId());
        dto.setCompanyId(app.getCompany().getId());
        dto.setStudentName(app.getStudent().getName());
        dto.setCompanyName(app.getCompany().getName());
        dto.setResumeLink(app.getResumeLink());
        dto.setStatus(app.getStatus());
        return dto;
    }
}
