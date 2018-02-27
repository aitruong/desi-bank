package org.email.message.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
@Service("mailServiceImpl")
public class MailServiceImpl implements MailService {
	
	@Autowired
	private MailSender mailSender;

	@Override
	public void sendMail(String from, String to, String subject, String body) {
		/*ApplicationContext app = new ClassPathXmlApplicationContext("spring-service.xml");
	    MailSender mailSender = (MailSender) app.getBean("mailSender");*/
		SimpleMailMessage message = new SimpleMailMessage();
		        message.setFrom(from);
		        message.setTo(to);
		        message.setSubject(subject);
		        message.setText(body);
		        mailSender.send(message);
		
	}

}
