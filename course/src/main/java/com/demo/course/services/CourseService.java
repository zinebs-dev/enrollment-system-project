package com.demo.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.course.entities.Course;
import com.demo.course.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepo; 
	
	public List<Course> getAllCourses(){
		return courseRepo.findAll();
	}
	
	public Optional<Course> getById(Long id) {
		return courseRepo.findById(id);
	}
	
	public Optional<Course> getByTitle(String title) {
		return courseRepo.findByTitle(title);
	}
	
	public Course addCourse(Course course) {
		return courseRepo.save(course);
	}
	
	public void deleteCourse(Long id) {
		courseRepo.deleteById(id);
	}
}
