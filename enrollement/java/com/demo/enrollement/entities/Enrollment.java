package com.demo.enrollement.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Data
@Entity
@Table(name="enrollements")
public class Enrollment {
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


private Long studentId;
private Long courseId;
private LocalDateTime enrollmentDate;

}
