package com.demo.enrollement.services;

import org.springframework.stereotype.Service;

import com.demo.enrollement.repository.EnrollmentRepository;

@Service

public class EnrollmentService {
	private EnrollmentRepository repository ; 
	
	public EnrollmentService(EnrollmentRepository repository) {
		this.repository=repository ; 
	}
	public void checkEnrollment() {
		this.repository.
	}

}
