package com.demo.student.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Table(name = "students")
@Entity
public class Student {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message="CNIE is required !")
@Column(unique=true)
private String cnie;

@NotBlank(message="First name is required !")
private String firstName;

@NotBlank(message="Last name is required !")
private String lastName;

@Email(message = "Please provide a valid email !")
@NotBlank(message="Email is required ! ")
@Column(unique = true)
private String email;


public Long getId() { return id; }
public String getCnie() { return cnie; }
public String getFirstName() { return firstName; }
public String getLastName() { return lastName; }
public String getEmail() { return email; }

public void setId(Long id) { this.id = id; }
public void setCnie(String cnie) { this.cnie = cnie; }
public void setFirstName(String firstName) { this.firstName = firstName; }
public void setLastName(String lastName) { this.lastName = lastName; }
public void setEmail(String email) { this.email = email; }

}