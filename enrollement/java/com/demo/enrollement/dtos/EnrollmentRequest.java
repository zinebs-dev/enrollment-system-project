package com.demo.enrollement.dtos;

public class EnrollmentRequest {
	private Long studentId;
	private Long courseId ; 
	
	public Long getStudentId() {return this.studentId;}
	public Long getCourseId() {return this.courseId; }
	
	public void setStudentId(Long studentId) {this.studentId = studentId ;}
	public void setCourseId(Long courseId) {this.courseId = courseId ;}
	
}
