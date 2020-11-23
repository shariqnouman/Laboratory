package com.cg.healthassist.laboratory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** This is a user defined exception for reports
 * 
 * @author Sharique Nooman
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ReportNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReportNotFoundException(String message){
		super(message);
	}
}
