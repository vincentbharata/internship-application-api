package com.example.internship_application.service;

import com.example.internship_application.model.Student;
import com.example.internship_application.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    private final StudentRepository studentRepo = mock(StudentRepository.class);
    private final StudentService studentService = new StudentService(studentRepo);

    @Test
    void testCreate_shouldSaveStudentCorrectly() {
        // Arrange
        Student request = new Student();
        request.setName("Jane Doe");
        request.setEmail("jane@example.com");

        Student saved = new Student();
        saved.setId(1L);
        saved.setName("Jane Doe");
        saved.setEmail("jane@example.com");

        when(studentRepo.save(any(Student.class))).thenReturn(saved);

        // Act
        Student result = studentService.create(request);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        assertEquals("jane@example.com", result.getEmail());

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepo, times(1)).save(captor.capture());

        Student captured = captor.getValue();
        assertEquals("Jane Doe", captured.getName());
        assertEquals("jane@example.com", captured.getEmail());
    }
}
