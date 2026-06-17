package com.demo.enrollement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.enrollement.clients.CourseClient;
import com.demo.enrollement.clients.StudentClient;
import com.demo.enrollement.dtos.EnrollmentRequest;
import com.demo.enrollement.dtos.EnrollmentResponseDTO;
import com.demo.enrollement.entities.Enrollment;
import com.demo.enrollement.repository.EnrollmentRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service

public class EnrollmentService {
	@Autowired
	private EnrollmentRepository repository ; 
	
	@Autowired
	private StudentClient studentClient;
	
	@Autowired
	private CourseClient courseClient;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public Enrollment checkEnrollment(EnrollmentRequest request) throws Exception {
		
		String studentCnie = request.getStudentCnie();

		String studentJson = studentClient.getStudentByCnie(studentCnie);
		if (studentJson == null) { //json
			throw new Exception("Etudiant  inexistant avec le Cnie : "+ studentCnie);
		}
		
		Long courseId = request.getCourseId();
	
		if(courseClient.getCourseById(courseId)==null) {
			throw new Exception("Cours inexistant avec l'Id : "+ courseId);
		}
		int count = repository.countByCourseId(courseId);
		if (count >= 3) {
			throw new Exception("Ce cours est deja remplie !");
		}
        
		//pour extraire studentId depuis le Json recu
		
		JsonNode student  = objectMapper.readTree(studentJson);
		Long studentId = student.get("id").asLong();
		
		if (repository.existsByStudentIdAndCourseId(studentId, courseId)) {
	        throw new Exception("Vous etes deja inscrit a ce cours !");
	    }
		
		Enrollment enrollment = new Enrollment();
		enrollment.setCourseId(courseId);
		enrollment.setEnrollmentDate(LocalDateTime.now());
		enrollment.setStudentId(studentId);
		
		return repository.save(enrollment);
	}
	
	public List<EnrollmentResponseDTO> getMyEnrollments(String cnie) throws Exception{
		String student=studentClient.getStudentByCnie(cnie);
		if(student==null) {
			throw new Exception("Etudiant inexistant  avec cnie : "+cnie+" !");
		}
		JsonNode studentJson = objectMapper.readTree(student);
		Long studentId =studentJson.get("id").asLong();
		
		List<Enrollment> myEnrollments = repository.findByStudentId(studentId);
		
		List<EnrollmentResponseDTO> result = new ArrayList<>();
		
		for (Enrollment enrollment : myEnrollments) {
			
			EnrollmentResponseDTO response = new EnrollmentResponseDTO();
			response.setEnrollmentId(enrollment.getId());
			response.setStudentCnie(cnie);
			String courseJson = courseClient.getCourseById(enrollment.getCourseId());
			if (courseJson == null) {
			    throw new Exception("Cours introuvable avec l'Id : " + enrollment.getCourseId());
			}
			JsonNode courseNode = objectMapper.readTree(courseJson);
			String courseName = courseNode.get("title").asText();
			response.setCourseName(courseName);
			response.setDate(enrollment.getEnrollmentDate().toString());
			response.setState(enrollment.getEnrollmentDate().isAfter(LocalDateTime.now().minusHours(24)));
			
			result.add(response);	
		}
		
		return result;
		
	}
	
	public void cancelEnrollement(Long enrollmentId ) throws Exception{
		Enrollment enrollment = repository.findById(enrollmentId).orElseThrow(()->new Exception("Enrollement introuvable ! "));

		boolean aSupprimer = enrollment.getEnrollmentDate().isAfter(LocalDateTime.now().minusHours(24));
		
		if (!aSupprimer) {
			throw new Exception("Vous ne pouvez pas annuler l'enrollement apres 24 heurs");
		}
		repository.deleteById(enrollmentId);
		
	}

}
