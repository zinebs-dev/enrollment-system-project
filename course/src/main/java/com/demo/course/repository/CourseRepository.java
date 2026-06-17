package com.demo.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.course.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course , Long>{
	 Optional<Course> findByTitle(String title) ;
}
