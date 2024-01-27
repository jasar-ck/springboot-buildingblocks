package com.hs.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Simple Custom error detail bean
public class CustomErrorDetails {
	
	private LocalDateTime timestamp;
	private String message;
	private String errorDetails;
	public CustomErrorDetails(LocalDateTime timestamp, String message, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorDetails = errorDetails;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime date) {
		this.timestamp = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	

}
