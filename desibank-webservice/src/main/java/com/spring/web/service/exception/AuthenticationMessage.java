package com.spring.web.service.exception;

//This is detail message bean
public class AuthenticationMessage {
	
	private String message;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
