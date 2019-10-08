package com.stacksimplify.restservices.exception;

import java.util.Date;

public class CustomErrorDetails {

	private Date timestamp;
	private String error;
	private String errorDetails;
	
	public CustomErrorDetails(Date timestamp, String error, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.error = error;
		this.errorDetails = errorDetails;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	
	
	
}
