package com.desi.bank.customer.soap.service.fault;

public class CustomerSoapException extends Exception {
	
	private CustomerSoapFault detail;
	/**
	 * 		
	 * @param message
	 * @param detail
	 */
	public CustomerSoapException(String message, CustomerSoapFault detail) {
		super(message);
		this.detail = detail;
	}
	
	
	/**
	 *  This is the special method and name should be getFaultInfo
	 * @return
	 */
	public CustomerSoapFault getExceptinInfo(){
		return detail;
	}

}
