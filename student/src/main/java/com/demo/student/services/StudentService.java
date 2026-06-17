package com.demo.student.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.student.entities.Student;
import com.demo.student.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository ; 
	
	public List<Student> getAllStudents(){
		return repository.findAll();
	}
	
	public Optional<Student> getStudentById(Long id ){
		return repository.findById(id);
	}
	
	public Optional<Student> getStudentByCnie(String cnie ){
		return repository.findByCnie(cnie);
	}	
	
	public Student addStudent(Student student) {
		return repository.save(student); 
	}
	
	public void deleteStudent(Long id ) {
		repository.deleteById(id);
	}
	
	
}
