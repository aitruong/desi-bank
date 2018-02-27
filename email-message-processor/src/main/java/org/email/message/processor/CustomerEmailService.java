package org.email.message.processor;

import java.io.StringWriter;
import java.net.URL;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("CustomerEmailService")
@Scope("singleton")
public class CustomerEmailService  implements ICustomerEmailService{
	
	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("velocityEngine")
	 private VelocityEngine velocityEngine;
	
	@Override
	@Async
	public String sendEnquiryConfirmation(String email,String name,String imageUrl) {
	
	//Here write code for sending email using the template
	MimeMessage message = mailSender.createMimeMessage();
	try {
		InternetAddress fromAddress = new InternetAddress(	"nagen@synergistic", "Aaron");
		message.setFrom(fromAddress);
		// message.setSender(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
		message.setSubject("Regarding Saving Account Opening Request!");
		message.setSentDate(new Date());
		
		// This mail has 2 part, the BODY and the embedded image
		MimeMultipart multipart = new MimeMultipart("related");
		// first part (the html)
		BodyPart messageBodyPart = new MimeBodyPart();
		Template template = velocityEngine.getTemplate("./templates/saving-account-confirmation.vm");
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("name", name);
		Date date=new Date();
		velocityContext.put("appNo", "AS-"+date.getTime());
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		System.out.println(" :-"+stringWriter.toString());
         messageBodyPart.setContent(stringWriter.toString(), "text/html");
		multipart.addBodyPart(messageBodyPart);
		 messageBodyPart = new MimeBodyPart();
         messageBodyPart.setDataHandler(new DataHandler(new URL(imageUrl)));
         messageBodyPart.setHeader("Content-ID", "<bankimage>");
         multipart.addBodyPart(messageBodyPart);
		// put everything together
		message.setContent(multipart);
		mailSender.send(message);
	} catch (Exception exe) {
		exe.printStackTrace();
	}
	 return "success";
	}
	
	 @Async
	@Override
	public void sendRegistrationEmail(EmailVO mail) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			InternetAddress fromAddress = new InternetAddress(	mail.getFrom(), "DesiBank Admin");
			message.setFrom(fromAddress);
			// message.setSender(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail.getTo()));
			message.setSubject(mail.getSubject());
			message.setSentDate(new Date());
			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");
			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			 //Classpath - >>> /WEB-INF/classes
			//this code is creating html template with dynamic data
			Template template = velocityEngine.getTemplate("./templates/customer-registration.vm");
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("cname", mail.getName());
			velocityContext.put("registrationlink", mail.getLink());
			StringWriter stringWriter = new StringWriter();
			template.merge(velocityContext, stringWriter);
			System.out.println(" :-"+stringWriter.toString());

			messageBodyPart.setContent(stringWriter.toString(), "text/html");
			multipart.addBodyPart(messageBodyPart);
			
			 messageBodyPart = new MimeBodyPart();
			 DataSource fds = new FileDataSource("C:/Users/VC1/workspace_multimodule/desibank-parent/email-message-processor/src/main/resources/bank-icon.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<banklogo>");
	         multipart.addBodyPart(messageBodyPart);
		
	         // put everything together
			message.setContent(multipart);
			mailSender.send(message);
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}
}

