package com.cg.healthassist.laboratory.exceptions;

import java.util.Date;

/** The ErrorDetails class captures the details of each errors like date and message
 * 
 * @author Sharique Nooman
 *
 */
public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
