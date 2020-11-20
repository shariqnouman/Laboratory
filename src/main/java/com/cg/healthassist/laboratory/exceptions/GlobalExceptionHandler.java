package com.cg.healthassist.laboratory.exceptions;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/** The GlobalExceptionHandler class has exception handler methods for different exceptions
 * 
 * @author Sharique Nooman
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/** Exception handler for global exception
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/** Exception handler for Appointment controller
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<Object> appointmentNotFoundException(AppointmentNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/** Exception handler for showing validation error messages
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex) {
		StringBuilder message = new StringBuilder();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		for (ConstraintViolation<?> violation : violations) {
			message.append(violation.getMessage().concat(", "));
		}
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Error", message.toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/** Exception handler for showing validation error messages
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation error", ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
