package com.desi.bank.email.service;

public interface MailService {
	
	public void sendMail(String from,String to,String subject,String body);

}
