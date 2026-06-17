package com.demo.student.controllers;

import java.util.List;
import java.util.Optional;

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

import com.demo.student.entities.Student;
import com.demo.student.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
	private StudentService service ; 
	
	@GetMapping
	public List<Student> getAllStudents(){
		return service.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id ){
		Optional<Student> student = service.getStudentById(id);
		if (student.isPresent()) {
			return ResponseEntity.ok(student.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/cnie/{cnie}")
	public ResponseEntity<Student> getStudentByCnie(@PathVariable String cnie ){
		Optional<Student> student = service.getStudentByCnie(cnie);
		if (student.isPresent()) {
			return ResponseEntity.ok(student.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student){
		//test throw
		Student savedStd = service.addStudent(student);
		return ResponseEntity.ok(savedStd);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Long id ){
		service.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}
	
	
	

}
