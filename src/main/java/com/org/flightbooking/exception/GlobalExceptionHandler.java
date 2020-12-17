package com.org.flightbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> customException(CustomException ex) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorResponse> loginException(LoginException ex) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);

	}
	
	@ExceptionHandler(SuccessLoginException.class)
	public ResponseEntity<ErrorResponse> successLoginException(SuccessLoginException ex) {

	ErrorResponse errorResponse = new ErrorResponse();

	errorResponse.setMessage(ex.getMessage());
	errorResponse.setStatus(HttpStatus.OK.value());

	return new ResponseEntity<>(errorResponse, HttpStatus.OK);

	}

}
