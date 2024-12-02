package com.example.studentservice.studentexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class StudentDetailsExectionsHandles {

	/**
	 * This method handles the StudentDetailsNotFound exception. It returns a
	 * NOT_FOUND status (404) along with the exception message.
	 */
	@ResponseStatus(code = HttpStatus.NOT_FOUND) // Returns HTTP 404 status when this exception occurs
	@ExceptionHandler(StudentDetailsNotFound.class) // Specifies that this method handles StudentDetailsNotFound
													// exceptions
	public ResponseEntity<Object> studentDetailsNotFoundException(StudentDetailsNotFound detailsNotFound) {
		// Returns a ResponseEntity with HTTP status 404 and the exception message in
		// the response body
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detailsNotFound.getMessage());
	}

	/**
	 * Handles HttpRequestMethodNotSupportedException, which is thrown when the HTTP
	 * method used for the request is not supported by the server for the given URL.
	 *
	 * @param supportedException - the exception object containing the details of
	 *                           the error
	 * @return ResponseEntity with HTTP status 405 (METHOD_NOT_ALLOWED) and the
	 *         exception message in the body
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> httpMethodInValid(HttpRequestMethodNotSupportedException supportedException) {

		// Create a ResponseEntity with 405 status and include the exception message in
		// the response body
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(supportedException.getMessage());
	}

	/**
	 * Handles NoResourceFoundException and returns a custom error message.
	 * 
	 * @param foundException the exception that was thrown when the resource was not
	 *                       found
	 * @return a ResponseEntity containing a 404 NOT FOUND status and a custom error
	 *         message
	 */
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Object> inValidEndPoints(NoResourceFoundException foundException) {

		// Creates a ResponseEntity with HttpStatus.NOT_FOUND and a custom message in
		// the response body
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EndPoints is Invalid");
	}

}
