package com.example.internship_application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.internship_application.dto.ApplicationRequest;
import com.example.internship_application.service.ApplicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService appService;

    @PostMapping
    public ResponseEntity<ApplicationRequest> apply(@Valid @RequestBody ApplicationRequest request) {
        return ResponseEntity.ok(appService.apply(request));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationRequest>> findAll() {
        return ResponseEntity.ok(appService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationRequest> findById(@PathVariable Long id) {
        return ResponseEntity.ok(appService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationRequest> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody ApplicationRequest request) {
        return ResponseEntity.ok(appService.updateStatus(id, request.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ApplicationRequest>> findByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(appService.findByStudentId(studentId));
    }
}
