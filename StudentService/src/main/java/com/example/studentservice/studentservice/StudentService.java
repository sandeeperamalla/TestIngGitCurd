package com.example.studentservice.studentservice;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentservice.studentdao.StudentDao;
import com.example.studentservice.studentdetails.StudentDetails;
import com.example.studentservice.studentdetails.StudentResponse;

@Service
public class StudentService {

	@Autowired
	StudentDao dao;

	@Autowired
	ModelMapper mapper;

	/**
	 * Service method to save student details and return the response.
	 *
	 * @param details - the student details to be saved
	 * @return StudentResponse - the response object containing saved student
	 *         information
	 */
	public StudentResponse saveStudentService(StudentDetails details) {

		// Check if the input details are not null
		if (details == null) {
			throw new IllegalArgumentException("Student details must not be null");
		}

		// Save the student details using the DAO layer
		StudentDetails studentDetails = dao.saveStudentDao(details);

		// Map the saved StudentDetails entity to a StudentResponse DTO
		StudentResponse studentResponse = mapper.map(studentDetails, StudentResponse.class);

		// Return the mapped response
		return studentResponse;
	}



	/**
	 * Retrieves all student details from the database and maps them to
	 * StudentResponse objects.
	 *
	 * @return List<StudentResponse> - a list of student response objects containing
	 *         student details
	 */
	public List<StudentResponse> getAllStudentDetailsService() {
		// Fetch all student details from the DAO layer
		List<StudentDetails> listOfStudentDetails = dao.findAllStudentsDao();

		// Map the list of StudentDetails to a list of StudentResponse using the mapper
		List<StudentResponse> listOfStudentResponses = mapper.map(listOfStudentDetails,
				new TypeToken<List<StudentResponse>>() {
				}.getType());

		// Return the list of StudentResponse objects
		return listOfStudentResponses;
	}

	/**
	 * Retrieves a student's details based on their rule number.
	 *
	 * @param ruleNumber The rule number of the student to be retrieved.
	 * @return A StudentResponse object containing the student's details.
	 */
	public StudentResponse getStudentByRuleNumberService(int ruleNumber) {
		// Fetch student details from the data access object (DAO) using the provided
		// rule number
		StudentDetails studentDetails = dao.getStudentByIdDao(ruleNumber);

		// Map the retrieved student details to the response object
		StudentResponse studentResponse = mapper.map(studentDetails, StudentResponse.class);

		// Return the populated StudentResponse object
		return studentResponse;
	}

	/**
	 * Deletes student details based on the provided rule number.
	 *
	 * @param ruleNumber - the unique identifier for the student to be deleted
	 * @return StudentResponse - the response containing details of the deleted
	 *         student
	 */
	public StudentResponse deleteStudentDetailsService(int ruleNumber) {
		// Call the DAO to delete the student details based on the rule number
		StudentDetails details = dao.deleteStudentDao(ruleNumber);

		// Check if the details are null (student not found) and handle accordingly

		// Map the deleted StudentDetails to a StudentResponse object
		StudentResponse studentResponse = mapper.map(details, StudentResponse.class);

		// Return the StudentResponse containing the deleted student's information
		return studentResponse;
	}

	/**
	 * Updates the details of a student.
	 *
	 * @param details - the student details to be updated
	 * @return StudentResponse - the response object containing updated student
	 *         details
	 * @throws IllegalArgumentException if the provided student details are null
	 */
	public StudentResponse updateStudentDetailsService(StudentDetails details) {
		// Check if the provided details are null
		if (details == null) {
			throw new IllegalArgumentException("Student details cannot be null");
		}

		// Call the DAO layer to update student details in the database
		StudentDetails studentDetails = dao.updateStudentDao(details); // Ensure dao method name matches purpose

		// Map the updated StudentDetails entity to StudentResponse DTO
		StudentResponse studentResponse = mapper.map(studentDetails, StudentResponse.class);

		// Return the mapped StudentResponse object
		return studentResponse;
	}
	
	
//	public List<StudentResponse> getAllStudentDetails(){
//	List<StudentDetails> listOfStudentDetails=dao.findAllStudents();
//	List<StudentResponse> listOfStudentresResponses=Arrays.asList(mapper
//			           .map(listOfStudentDetails, StudentResponse.class));
//	return listOfStudentresResponses;
//}

}
