package com.example.studentservice.studentcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentservice.studentdetails.StudentDetails;
import com.example.studentservice.studentdetails.StudentResponse;
import com.example.studentservice.studentservice.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;

	/**
	 * Controller method to handle HTTP POST requests for creating a new student.
	 * The service layer is responsible for handling any potential errors.
	 * 
	 * @param details - The student details received in the request body
	 * @return ResponseEntity containing the saved student response
	 * 
	 */
	@PostMapping("/newStudent")
	public ResponseEntity<StudentResponse> saveStudentController(@RequestBody StudentDetails details) {
		// Call the service to save the student details and return the response

		StudentResponse response = studentService.saveStudentService(details);

		// Set the response status to CREATED (201) and add the response body
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * REST controller method to fetch details of all students. This method is
	 * mapped to the GET request at the "/getAllStudents" end point.
	 * 
	 * Note: Errors and exceptions are handled in the service layer.
	 * 
	 * @return ResponseEntity containing a list of StudentResponse objects with an
	 *         HTTP OK status
	 */
	@GetMapping("/getAllStudents")
	public ResponseEntity<List<StudentResponse>> getAllstudentDetailsController() {

		// Fetch the list of student details from the service layer
		List<StudentResponse> studentResponses = studentService.getAllStudentDetailsService();

		// Return the list wrapped in a ResponseEntity with HTTP status OK (200)
		return ResponseEntity.status(HttpStatus.OK).body(studentResponses);
	}

	/**
	 * Controller method to retrieve student details by ID.
	 *
	 * @param id - The ID of the student whose details are to be retrieved.
	 * @return ResponseEntity containing the student details and the HTTP status.
	 */
	@GetMapping("/getStudentDetailsById/{id}")
	public ResponseEntity<StudentResponse> getStudentDetailsByIdController(@PathVariable int id) {
		// Call the service method to get student details by rule number
		StudentResponse studentResponse = studentService.getStudentByRuleNumberService(id);

		// Return a response entity with status OK and the retrieved student details
		return ResponseEntity.status(HttpStatus.OK) // Sets the HTTP status to 200 OK
				.body(studentResponse); // Sets the body of the response to the student details
	}

	/**
	 * Deletes a student based on the provided rule number (id).
	 * 
	 * @param id - the identifier of the student to be deleted
	 * @return ResponseEntity containing the status and body of the response
	 */
	@DeleteMapping("/StudentDetailsByRulenumber/{id}")
	public ResponseEntity<StudentResponse> deleteStudentController(@PathVariable int id) {
		// Calls the service layer to delete the student details and returns the
		// response
		return ResponseEntity.status(HttpStatus.OK).body(studentService.deleteStudentDetailsService(id));
	}

	/**
	 * Updates student details based on the provided StudentDetails object.
	 *
	 * @param details - the StudentDetails object containing updated information
	 * @return ResponseEntity containing the updated StudentResponse object and HTTP
	 *         status
	 */
	@PutMapping("/updateStudent") // Maps HTTP PUT requests to this method
	public ResponseEntity<StudentResponse> updateStudentController(@RequestBody StudentDetails details) {
		// Calls the service to update the student details and returns the response
		return ResponseEntity.status(HttpStatus.OK) // Sets HTTP status to 200 OK
				.body(studentService.updateStudentDetailsService(details)); // Returns the updated student response
	}
}
