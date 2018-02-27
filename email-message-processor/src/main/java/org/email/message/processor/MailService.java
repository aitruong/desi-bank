package org.email.message.processor;

public interface MailService {
	
	public void sendMail(String from,String to,String subject,String body);

}
