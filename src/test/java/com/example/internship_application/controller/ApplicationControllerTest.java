package com.example.internship_application.controller;

import com.example.internship_application.dto.ApplicationRequest;
import com.example.internship_application.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ApplicationControllerTest {

    private final ApplicationService appService = mock(ApplicationService.class);
    private final ApplicationController controller = new ApplicationController(appService);

    @Test
    void testApplySuccess() {
        ApplicationRequest request = dummyRequest();
        when(appService.apply(any())).thenReturn(request);

        ResponseEntity<ApplicationRequest> response = controller.apply(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("PENDING", response.getBody().getStatus());
        verify(appService).apply(request);
    }

    @Test
    void testFindAll() {
        when(appService.findAll()).thenReturn(List.of(dummyRequest()));

        ResponseEntity<List<ApplicationRequest>> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(appService).findAll();
    }

    @Test
    void testFindById() {
        ApplicationRequest request = dummyRequest();
        when(appService.findById(1L)).thenReturn(request);

        ResponseEntity<ApplicationRequest> response = controller.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Malik", response.getBody().getStudentName());
        verify(appService).findById(1L);
    }

    @Test
    void testUpdateStatus() {
        ApplicationRequest updated = dummyRequest();
        updated.setStatus("ACCEPTED");

        when(appService.updateStatus(1L, "ACCEPTED")).thenReturn(updated);

        ApplicationRequest updateRequest = new ApplicationRequest();
        updateRequest.setStatus("ACCEPTED");

        ResponseEntity<ApplicationRequest> response = controller.updateStatus(1L, updateRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ACCEPTED", response.getBody().getStatus());
        verify(appService).updateStatus(1L, "ACCEPTED");
    }

    @Test
    void testDelete() {
        doNothing().when(appService).delete(1L);

        ResponseEntity<Void> response = controller.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(appService).delete(1L);
    }

    @Test
    void testFindByStudentId() {
        when(appService.findByStudentId(1L)).thenReturn(List.of(dummyRequest()));

        ResponseEntity<List<ApplicationRequest>> response = controller.findByStudent(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(appService).findByStudentId(1L);
    }

    private ApplicationRequest dummyRequest() {
        ApplicationRequest req = new ApplicationRequest();
        req.setStudentId(1L);
        req.setStudentName("Malik");
        req.setCompanyId(2L);
        req.setCompanyName("PT. Maju Mundur");
        req.setResumeLink("https://resume.com");
        req.setStatus("PENDING");
        return req;
    }
}