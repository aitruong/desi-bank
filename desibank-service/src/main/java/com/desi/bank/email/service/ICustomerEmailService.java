package com.desi.bank.email.service;

import com.desi.bank.employee.web.controller.form.CustomerAccountRegistrationVO;

/**
 * 
 * @author VC1
 *
 */
public interface ICustomerEmailService {

	public void sendRegistrationEmail(EmailVO mail);
	public String sendEnquiryConfirmation(String email, String name, String imageUrl);
	public String sendAccountCreationEmail(CustomerAccountRegistrationVO customerAccountRegistrationVO);

}
