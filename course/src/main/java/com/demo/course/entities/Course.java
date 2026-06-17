package com.demo.course.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="courses")
public class Course {

@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message="The title is required !")
private String title;

private String description;

private int credits;


public Long getId() { return id; }
public String getTitle() { return title; }
public String getDescription() { return description; }
public int getCredits() { return credits; }

public void setId(Long id) { this.id = id; }
public void setTitle(String title) { this.title = title; }
public void setDescription(String description) { this.description = description; }
public void setCredits(int credits) { this.credits = credits; }


}
