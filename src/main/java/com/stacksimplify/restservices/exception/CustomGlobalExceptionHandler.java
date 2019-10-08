package com.stacksimplify.restservices.exception;

import java.util.Date;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customerrordetails = new CustomErrorDetails(new Date(),"From Method Argument Not Valid",ex.getMessage());
		
		return new ResponseEntity<Object>(customerrordetails,HttpStatus.BAD_REQUEST);
	}
	
	
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
        CustomErrorDetails customerrordetails = new CustomErrorDetails(new Date(),"From Method Argument Not Valid-Http not supported",ex.getMessage());
		
		return new ResponseEntity<Object>(customerrordetails,HttpStatus.METHOD_NOT_ALLOWED);
		
	}
	@ExceptionHandler(UserNameNotFoundException.class)
	protected ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex,WebRequest request){
						
		
        CustomErrorDetails customerrordetails = new CustomErrorDetails(new Date(),"From Method Argument Not Valid-UserName Not Found",request.getDescription(false));
		
		return new ResponseEntity<Object>(customerrordetails,HttpStatus.NOT_FOUND);
	}

}
