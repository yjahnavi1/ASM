package com.cg.aps.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;



//@ControllerAdvice
@RestControllerAdvice // Global exception handling
public class ApplicationException 
{
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 String exceptionHandler(Exception ex) {
	 return ex.getMessage();
	 }	
}