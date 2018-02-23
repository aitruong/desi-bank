package com.spring.web.service.exception;


/**
 * 
 * @author yadna01
 *   creating custom exception..............
 *
 */
public class UnAuthorizedUserException  extends Exception{
	
	private UnAuthenicateUser unAuthenicateUser;
	
	public UnAuthorizedUserException() {
		super();
	}

	/**
	 * 
	 * @param message =>Generic Message
	 * @param unAuthenicateUser
	 *   Detailed and actual cause of the error
	 */
	public UnAuthorizedUserException(String message,UnAuthenicateUser unAuthenicateUser) {
		super(message);
		this.unAuthenicateUser=unAuthenicateUser;
	}
	
	/**
	 * 
	 * @return
	 *   Return pojo which carry the exception message
	 *   It's method name must be getFaultInfo
	 */
	public UnAuthenicateUser getFaultInfo(){
		return unAuthenicateUser;
	}


}
