package com.demo.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.student.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	Optional<Student> findByCnie(String cnie);
}
