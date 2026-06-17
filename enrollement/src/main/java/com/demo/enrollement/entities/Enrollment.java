package com.demo.enrollement.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="enrollements")
public class Enrollment {
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


private Long studentId;
private Long courseId;
private LocalDateTime enrollmentDate;

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public Long getStudentId() { return studentId; }
public void setStudentId(Long studentId) { this.studentId = studentId; }

public Long getCourseId() { return courseId; }
public void setCourseId(Long courseId) { this.courseId = courseId; }

public LocalDateTime getEnrollmentDate() { return enrollmentDate; }
public void setEnrollmentDate(LocalDateTime date) { this.enrollmentDate = date; }

}
