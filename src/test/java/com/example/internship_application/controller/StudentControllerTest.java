package com.example.internship_application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.ResponseEntity;

import com.example.internship_application.model.Student;
import com.example.internship_application.service.StudentService;

class StudentControllerTest {

    @Test
    void testCreate_shouldReturnCreatedStudent() {
        // Arrange
        StudentService mockService = mock(StudentService.class);
        StudentController controller = new StudentController(mockService);

        Student request = new Student();
        request.setName("Jane Doe");
        request.setEmail("jane@example.com");

        Student saved = new Student();
        saved.setId(1L);
        saved.setName("John Doe");
        saved.setEmail("john@example.com");

        when(mockService.create(request)).thenReturn(saved);

        // Act
        ResponseEntity<Student> response = controller.create(request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getName());
        assertEquals("john@example.com", response.getBody().getEmail());

        verify(mockService, times(1)).create(request);
    }
}