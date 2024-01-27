package com.hs.exception;

import java.time.LocalDateTime;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.validation.ConstraintViolationException;

//Golbal Excpetion Handler Class - Applicable for all Controller


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		//return super.handleMethodArgumentNotValid(ex, headers, status, request);
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), "From MetodArgumentNotValidException in GEH", ex.getMessage());
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), "From HTTP Request Method not support1w in GEH", ex.getMessage());
		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFound(UserNameNotFoundException ex,
			 WebRequest request) {
		// TODO Auto-generated method stub
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			 WebRequest request) {
		// TODO Auto-generated method stub
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderNotFoundExcpetion.class)
	public final ResponseEntity<Object> handleCOrderNotFoundExcpetion(OrderNotFoundExcpetion ex,
			 WebRequest request) {
		// TODO Auto-generated method stub
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
	
}
