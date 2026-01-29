package com.example.internship_application.controller;

import com.example.internship_application.dto.CompanyRequest;
import com.example.internship_application.model.Company;
import com.example.internship_application.service.CompanyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyControllerTest {

    @Test
    void testSendFeedback_shouldReturnCreatedCompany() {
        // Arrange
        CompanyService mockService = mock(CompanyService.class);
        CompanyController controller = new CompanyController(mockService);

        CompanyRequest request = new CompanyRequest();
        request.setName("PT. Maju Mundur");

        Company saved = new Company();
        saved.setId(1L);
        saved.setName("PT. Maju Mundur");

        when(mockService.sendCompany(request)).thenReturn(saved);

        // Act
        Company response = controller.sendFeedback(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("PT. Maju Mundur", response.getName());

        verify(mockService, times(1)).sendCompany(request);
    }
}

