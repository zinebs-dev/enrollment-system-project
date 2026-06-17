package com.demo.course.controllers;

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

import com.demo.course.entities.Course;
import com.demo.course.services.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private CourseService service;
	
	@GetMapping
	public List<Course> getCourses(){
		return service.getAllCourses();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id ){
		Optional<Course> course =service.getById(id);
		if(course.isPresent()) {
			return ResponseEntity.ok(course.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<Course> getByTitle(@PathVariable String title){
		Optional<Course> course = service.getByTitle(title);
		if(course.isPresent()) {
			return ResponseEntity.ok(course.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<Course> addCourse(@Valid @RequestBody Course course) {
		Course cours = service.addCourse(course);
		return ResponseEntity.ok(cours);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id ) {
		service.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}
}
