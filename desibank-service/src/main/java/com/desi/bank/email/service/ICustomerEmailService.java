package com.desi.bank.email.service;

/**
 * 
 * @author VC1
 *
 */
public interface ICustomerEmailService {

	public void sendRegistrationEmail(EmailVO mail);
	public String sendEnquiryConfirmation(String email, String name, String imageUrl);

}
