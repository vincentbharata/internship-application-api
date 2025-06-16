package com.example.internship_application.service;

import com.example.internship_application.dto.CompanyRequest;
import com.example.internship_application.model.Company;
import com.example.internship_application.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService (CompanyRepository companyRepository){this.companyRepository = companyRepository;}

    public Company sendCompany(CompanyRequest request){
        Company company = new Company();
        company.setName(request.getName());
        return companyRepository.save(company);
    }

}
