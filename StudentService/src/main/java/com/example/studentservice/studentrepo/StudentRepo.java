package com.example.studentservice.studentrepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentservice.studentdetails.StudentDetails;

public interface StudentRepo extends JpaRepository<StudentDetails, Integer> {

}
