package com.example.internship_application.service;

import com.example.internship_application.dto.CompanyRequest;
import com.example.internship_application.model.Company;
import com.example.internship_application.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    public void testSendCompany_shouldSaveAndReturnCompany() {
        // Arrange
        CompanyRequest request = new CompanyRequest();
//        request.setName(" ");

        Company savedCompany = new Company();
        savedCompany.setId(1L);
//        savedCompany.setName("");

        when(companyRepository.save(any(Company.class))).thenReturn(savedCompany);

        // Act
        Company result = companyService.sendCompany(request);

        // Assert
        assertNotNull(result);
        assertEquals("Willman", result.getName());
    }
}
