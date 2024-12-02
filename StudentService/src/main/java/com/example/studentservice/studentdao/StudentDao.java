package com.example.studentservice.studentdao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.studentservice.studentdetails.StudentDetails;
import com.example.studentservice.studentexception.StudentDetailsNotFound;
import com.example.studentservice.studentrepo.StudentRepo;

@Repository
public class StudentDao {

	@Autowired
	StudentRepo repo;

	/**
	 * This method saves the given student details into the database.
	 * 
	 * @param details The StudentDetails object that contains the information of the
	 *                student to be saved.
	 * 
	 * @return The saved StudentDetails object after being persisted in the
	 *         database.
	 */

	public StudentDetails saveStudentDao(StudentDetails details) {
		// Save the student details in the repository and return the persisted entity
		return repo.save(details);
	}

	/**
	 * This method retrieves all the student details from the repository.
	 * 
	 * @return a list of all StudentDetails objects from the database.
	 */

	public List<StudentDetails> findAllStudentsDao() {
		// Calls the repository's findAll() method to fetch all records from the
		// database
		return repo.findAll();
	}

	/**
	 * Retrieves the StudentDetails object based on the provided ID.
	 *
	 * @param id - the ID of the student whose details are to be retrieved
	 * @return the StudentDetails object if found
	 * @throws StudentDetailsNotFound if no student exists with the provided ID
	 */
	public StudentDetails getStudentByIdDao(int id) {
		// Attempt to retrieve the student details from the repository by ID
		Optional<StudentDetails> studentDetailsOptional = repo.findById(id);

		// Check if the retrieved value is empty (i.e., no student found)
		if (studentDetailsOptional.isEmpty()) {
			// Throw custom exception with a descriptive message if not found
			throw new StudentDetailsNotFound("StudentDetails with ID: " + id + " does not exist");
		}

		// Return the StudentDetails object if found
		return studentDetailsOptional.get();
	}

	/**
	 * Deletes a student with the given ID from the repository.
	 *
	 * @param id - the ID of the student to be deleted
	 * @return the deleted StudentDetails object if found and deleted; otherwise,
	 *         null
	 * @throws StudentDetailsNotFound if the student with the given ID does not
	 *                                exist
	 */
	public StudentDetails deleteStudentDao(int id) {
		// Retrieve the student details from the repository using the provided ID
		Optional<StudentDetails> getById = repo.findById(id);

		// Check if the student details exist
		if (getById.isPresent()) {
			// Delete the student details if present
			StudentDetails student = getById.get(); // Get the student details
			repo.delete(student); // Delete the student from the repository
			return student; // Return the deleted student details
		} else {
			// If the student details do not exist, throw an exception
			throw new StudentDetailsNotFound("Student details with ID " + id + " do not exist");
		}
	}



	/**
	 * Updates the details of a student.
	 *
	 * @param details - the StudentDetails object containing updated information
	 * @return the updated StudentDetails object
	 * @throws StudentDetailsNotFound if the student with the given ID does not
	 *                                exist
	 */
	public StudentDetails updateStudentDao(StudentDetails details) {
		// Retrieve the student details from the repository using the unique identifier
		// (assumed to be student ID)
		Optional<StudentDetails> existingStudent = repo.findById(details.getStudRuleNumber()); // Assuming `getStuId()` retrieves
																						// the student's unique ID

		// Check if the student exists in the repository
		if (existingStudent.isEmpty()) {
			// Throw a custom exception if the student does not exist
			throw new StudentDetailsNotFound("StudentDetails with ID " + details.getStudRuleNumber() + " does not exist.");
		}

		// Update the existing student details with the new information
		StudentDetails studentToUpdate = existingStudent.get();
		studentToUpdate.setStuFirstName(details.getStuFirstName());
		studentToUpdate.setStuLastName(details.getStuLastName());
		studentToUpdate.setStuFatherName(details.getStuFatherName());
		studentToUpdate.setStuClass(details.getStuClass());
		studentToUpdate.setStuAge(details.getStuAge());

		// Save and return the updated student details
		return repo.save(studentToUpdate);
	}
	
	
	// old
//	public StudentDetails updateProduct(StudentDetails details) {
//	Optional<StudentDetails> existingStudent  = repo.findById(details.getStuAge());
//	if (existingStudent.isEmpty()) {
//		throw new StudentDetailsNotFound("StudentDetails With" + details.getStuAge() + "is not exist");
//	}
//	return repo.save(details);
//}

}
