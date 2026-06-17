package com.demo.enrollement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.enrollement.dtos.EnrollmentRequest;
import com.demo.enrollement.dtos.EnrollmentResponseDTO;
import com.demo.enrollement.entities.Enrollment;
import com.demo.enrollement.services.EnrollmentService;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService service;
	
    @PostMapping
    public ResponseEntity<?> enrollStudent(@RequestBody EnrollmentRequest request) {
        try {
            Enrollment enrollment = service.checkEnrollment(request);
            return ResponseEntity.ok(enrollment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @GetMapping("/{cnie}")
    public ResponseEntity<?> getMyEnrollments(@PathVariable String cnie) {
        try {
            List<EnrollmentResponseDTO> enrollments = service.getMyEnrollments(cnie);
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelEnrollment(@PathVariable Long id) {
        try {
            service.cancelEnrollement(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
	
	
}
