package com.example.studentservice.studentdetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class StudentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studRuleNumber;
	private String stuFirstName;
	private String stuLastName;
	private String stuFatherName;
	private String stuClass;
	private int stuAge;

}
