package com.example.studentservice.studentexception;

/**
 * Custom exception class for handling cases where student details are not
 * found. Extends the RuntimeException class to provide unchecked exception
 * handling.
 */
public class StudentDetailsNotFound extends RuntimeException {

	/**
	 * 
	 */
	// Unique identifier for serialization of this class
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that accepts a custom message when the exception is thrown.
	 *
	 * @param msg - the custom message to be included with the exception
	 */
	public StudentDetailsNotFound(String msg) {
		super(msg);// Passes the message to the superclass (RuntimeException)
	}

}
