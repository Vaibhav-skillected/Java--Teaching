package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeler {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handelEmployeeNotFound(EmployeeNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<String> handelEmailAlreadyExists(EmailAlreadyExistsException ex){
		return new ResponseEntity<>(ex.getMessage(),
				HttpStatus.BAD_REQUEST);
	}

}

//@RestControllerAdvice :- it cacthecs exception from all controllers in one place

//@ExceptionHandler :- it tells which methods should handel exceptions particalular exception

//ResponseEntity :- it allows to return both  response body and the appropriate Http status code 

//2oo 


