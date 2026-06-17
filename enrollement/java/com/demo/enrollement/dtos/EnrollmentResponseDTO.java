package com.demo.enrollement.dtos;

public class EnrollmentResponseDTO {
	private Long enrollmentId;
	private String studentCnie;
	private String courseName;
	private String date;
	private boolean deletable;
	
	public Long getEnrollmentId() {return this.enrollmentId;}
	public String getStudentCnie() {return this.studentCnie;}
	public String getCourseName() {return this.courseName;}
	public String getDate() {return this.date;}
	public boolean getState() {return this.deletable;}
	
	public void setEnrollmentId(Long enrollmentId) {this.enrollmentId=enrollmentId;}
	public void setStudentCnie(String studentCnie) {this.studentCnie = studentCnie; }
	public void setCourseName(String courseName) {this.courseName = courseName;}
	public void setDate(String date) {this.date = date ; }
	public void setState(boolean deletable) {this.deletable = deletable ; }
}