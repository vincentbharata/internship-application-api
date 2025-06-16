package com.example.internship_application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.internship_application.dto.CompanyRequest;
import com.example.internship_application.model.Company;
import com.example.internship_application.repository.CompanyRepository;

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
        request.setName("Willman");

        Company savedCompany = new Company();
        savedCompany.setId(1L);
        savedCompany.setName("Willman");

        when(companyRepository.save(any(Company.class))).thenReturn(savedCompany);

        // Act
        Company result = companyService.sendCompany(request);

        // Assert
        assertNotNull(result);
        assertEquals("Willman", result.getName());
    }
}
