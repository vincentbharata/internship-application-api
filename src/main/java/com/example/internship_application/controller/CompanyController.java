package com.example.internship_application.controller;

import com.example.internship_application.dto.CompanyRequest;
import com.example.internship_application.model.Company;
import com.example.internship_application.service.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService){this.companyService = companyService;}

    @PostMapping
    public Company sendFeedback(@RequestBody CompanyRequest request) {
        return companyService.sendCompany(request);
    }
}
