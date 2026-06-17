package com.demo.enrollement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.enrollement.entities.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
	
	public int countByCourseId(Long id);
	
	public List<Enrollment> findByStudentId(Long id);
	
	boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
	
}
