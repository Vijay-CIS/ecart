package com.demo.ecart.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException e) {
	ErrorResponse error = new ErrorResponse();
	error.setCode("EX1005");
	error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}
	
	@ExceptionHandler(UserNotLoggedInException.class)
	public ResponseEntity<ErrorResponse> userNotLoggedInExceptionHandler(UserNotLoggedInException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1012");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}
	@ExceptionHandler(InvalidProductNameException.class)
	public ResponseEntity<ErrorResponse> invalidProductNameException(InvalidProductNameException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1004");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> productNotFoundException(ProductNotFoundException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1003");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}
	
	@ExceptionHandler(InsufficientQuantityException.class)
	public ResponseEntity<ErrorResponse> insufficientQuantityException(InsufficientQuantityException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1013");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<ErrorResponse> fileException(FileException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1013");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> validationHandlerException(MethodArgumentNotValidException ex) {
      ValidationResponse validation = new ValidationResponse();
      
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		validation.setValidationMsg(errorMap);
		validation.setErrorCode("EX-0001");
		validation.setErrorMessage("Enter the valid details");
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}
	
	

}
