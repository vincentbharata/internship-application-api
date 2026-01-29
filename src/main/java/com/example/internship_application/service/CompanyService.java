package com.example.internship_application.service;

import com.example.internship_application.dto.CompanyRequest;
import com.example.internship_application.model.Company;
import com.example.internship_application.repository.ApplicationRepository;
import com.example.internship_application.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company sendCompany(CompanyRequest request){
        Company company = new Company();
        company.setName(request.getName());
        return companyRepository.save(company);
    }

}
