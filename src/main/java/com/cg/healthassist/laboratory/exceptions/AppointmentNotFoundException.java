package com.cg.healthassist.laboratory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** This is a user defined exception for appointments
 * 
 * @author Sharique Nooman
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AppointmentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppointmentNotFoundException(String message){
		super(message);
	}
}