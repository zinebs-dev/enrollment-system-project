package com.demo.enrollement.dtos;

public class EnrollmentRequest {
	private String studentCnie;
	private Long courseId ; 
	
	public String getStudentCnie() {return this.studentCnie;}
	public Long getCourseId() {return this.courseId; }
	
	public void setStudentCnie(String studentCnie) {this.studentCnie= studentCnie;}
	public void setCourseId(Long courseId) {this.courseId = courseId ;}
	
}
