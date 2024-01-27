package com.hs.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {

	public GlobalRestControllerExceptionHandler() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails userNameNotFound(UserNameNotFoundException ex) {
		return new CustomErrorDetails(LocalDateTime.now(), "User Name Not Found @RestControllerAdvice", ex.getMessage());
	}
	
}
