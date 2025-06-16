package com.example.internship_application.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.internship_application.dto.ApplicationRequest;
import com.example.internship_application.model.Application;
import com.example.internship_application.model.Company;
import com.example.internship_application.model.Student;
import com.example.internship_application.repository.ApplicationRepository;
import com.example.internship_application.repository.CompanyRepository;
import com.example.internship_application.repository.StudentRepository;

public class ApplicationTest {

    private final ApplicationRepository appRepo = mock(ApplicationRepository.class);
    private final StudentRepository studentRepo = mock(StudentRepository.class);
    private final CompanyRepository companyRepo = mock(CompanyRepository.class);

    private final ApplicationService appService = new ApplicationService(appRepo, studentRepo, companyRepo);

    @Test
    void testApplySuccess() {
        Student student = new Student(1L, "Malik", "malik@mail.com");
        Company company = new Company(2L, "PT. Maju Mundur");

        ApplicationRequest request = new ApplicationRequest();
        request.setStudentId(1L);
        request.setCompanyId(2L);
        request.setResumeLink("https://resume.com");

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(companyRepo.findById(2L)).thenReturn(Optional.of(company));
        when(appRepo.save(any(Application.class))).thenAnswer(inv -> inv.getArgument(0));

        ApplicationRequest result = appService.apply(request);

        assertEquals("PENDING", result.getStatus());
        assertEquals("Malik", result.getStudentName());
        assertEquals("PT. Maju Mundur", result.getCompanyName());
    }

    @Test
    void testApplyStudentNotFound() {
        ApplicationRequest request = new ApplicationRequest();
        request.setStudentId(1L);
        request.setCompanyId(2L);
        request.setResumeLink("https://resume.com");

        when(studentRepo.findById(1L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> appService.apply(request));
        assertEquals("Student not found", ex.getMessage());
    }

    @Test
    void testApplyCompanyNotFound() {
        Student student = new Student(1L, "Malik", "malik@mail.com");
        ApplicationRequest request = new ApplicationRequest();
        request.setStudentId(1L);
        request.setCompanyId(2L);
        request.setResumeLink("https://resume.com");

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(companyRepo.findById(2L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> appService.apply(request));
        assertEquals("Company not found", ex.getMessage());
    }

    @Test
    void testFindAll() {
        Application app = dummyApplication();
        when(appRepo.findAll()).thenReturn(List.of(app));

        List<ApplicationRequest> result = appService.findAll();
        assertEquals(1, result.size());
        assertEquals("Malik", result.get(0).getStudentName());
    }

    @Test
    void testFindByIdSuccess() {
        Application app = dummyApplication();
        when(appRepo.findById(1L)).thenReturn(Optional.of(app));

        ApplicationRequest result = appService.findById(1L);
        assertEquals("Malik", result.getStudentName());
    }

    @Test
    void testFindByIdNotFound() {
        when(appRepo.findById(99L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> appService.findById(99L));
        assertEquals("Application not found", ex.getMessage());
    }

    @Test
    void testUpdateStatusSuccess() {
        Application app = dummyApplication();
        when(appRepo.findById(1L)).thenReturn(Optional.of(app));
        when(appRepo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        ApplicationRequest result = appService.updateStatus(1L, "ACCEPTED");

        assertEquals("ACCEPTED", result.getStatus());
    }

    @Test
    void testUpdateStatusInvalid() {
        Application app = dummyApplication();
        when(appRepo.findById(1L)).thenReturn(Optional.of(app));

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                appService.updateStatus(1L, "INVALID"));
        assertEquals("Invalid status: INVALID", ex.getMessage());
    }

    @Test
    void testUpdateStatusNotFound() {
        when(appRepo.findById(99L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                appService.updateStatus(99L, "ACCEPTED"));
        assertEquals("Application not found", ex.getMessage());
    }

    @Test
    void testDelete() {
        doNothing().when(appRepo).deleteById(1L);
        assertDoesNotThrow(() -> appService.delete(1L));
        verify(appRepo, times(1)).deleteById(1L);
    }

    @Test
    void testFindByStudentId() {
        Application app = dummyApplication();
        when(appRepo.findByStudentId(1L)).thenReturn(List.of(app));

        List<ApplicationRequest> result = appService.findByStudentId(1L);
        assertEquals(1, result.size());
        assertEquals("Malik", result.get(0).getStudentName());
    }

    private Application dummyApplication() {
        Student student = new Student(1L, "Malik", "malik@mail.com");
        Company company = new Company(2L, "PT. Maju Mundur");

        Application app = new Application();
        app.setId(1L);
        app.setStudent(student);
        app.setCompany(company);
        app.setResumeLink("https://resume.com");
        app.setStatus("PENDING");

        return app;
    }
}
