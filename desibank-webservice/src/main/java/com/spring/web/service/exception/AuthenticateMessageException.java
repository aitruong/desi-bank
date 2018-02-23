package com.spring.web.service.exception;

/**
 * 
 * @author ADMIN-2
 *
 */
public class AuthenticateMessageException extends Exception {
	
	private AuthenticationMessage authenticationMessage;

	public AuthenticateMessageException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticateMessageException(String message,AuthenticationMessage authenticationMessage) {
		super(message);
		this.authenticationMessage=authenticationMessage;
	}
	
	
	public AuthenticationMessage getFaultInfo(){
		return authenticationMessage;
	}

}
