package com.example.internship_application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.internship_application.dto.CompanyRequest;
import com.example.internship_application.model.Company;
import com.example.internship_application.service.CompanyService;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor

public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public Company sendFeedback(@RequestBody CompanyRequest request) {
        return companyService.sendCompany(request);
    }
}
