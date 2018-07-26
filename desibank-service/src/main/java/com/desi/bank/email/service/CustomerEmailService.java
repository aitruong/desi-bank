package com.desi.bank.email.service;

import java.io.StringWriter;
import java.net.URL;
import java.util.Date;

import javax.activation.DataHandler;
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

import com.desi.bank.employee.web.controller.form.CustomerAccountRegistrationVO;

@Service("CustomerEmailService")
@Scope("singleton")
public class CustomerEmailService  implements ICustomerEmailService{
	
	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("velocityEngine")
	 private VelocityEngine velocityEngine;
	
	 @Async
	@Override
	public String sendAccountCreationEmail(CustomerAccountRegistrationVO customerAccountRegistrationVO) {
		 //Here write code for sending email using the template
		  MimeMessage message = mailSender.createMimeMessage();
		  try {
			    InternetAddress fromAddress = new InternetAddress("synergisticit2020@gmail.com", "DesiBank PVT. LTD.");
				message.setFrom(fromAddress);
				// message.setSender(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(customerAccountRegistrationVO.getTo()));
				message.setSubject(customerAccountRegistrationVO.getSubject());
				message.setSentDate(new Date());
				//compose the content of the email
				
				// This mail has 2 part, the BODY and the embedded image
				//multipart is a body of the email
				MimeMultipart multipart = new MimeMultipart("related");
				
				// first part (the html)
				//creating first of of the body of the email
				BodyPart messageBodyPart = new MimeBodyPart();
				//loading vm template
				Template template = velocityEngine.getTemplate("./templates/customer-account-creation-summary.vm");
				
				//below is used to send data from java to vm template
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("account", customerAccountRegistrationVO);
				//now merge velocityeContext & VM
				StringWriter stringWriter = new StringWriter();
				template.merge(velocityContext, stringWriter);
				System.out.println(" :-"+stringWriter.toString());
		        messageBodyPart.setContent(stringWriter.toString(), "text/html");
		        multipart.addBodyPart(messageBodyPart);
		        
		        //Now creating another BodyPart for img
		        messageBodyPart = new MimeBodyPart();
		         messageBodyPart.setDataHandler(new DataHandler(new URL(customerAccountRegistrationVO.getImage())));
		         messageBodyPart.setHeader("Content-ID", "<thanks>");
		         multipart.addBodyPart(messageBodyPart);
		         
				// put everything together
				message.setContent(multipart);
				mailSender.send(message);
			
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		 return "success";
    }
	
	@Override
	@Async
	public String sendEnquiryConfirmation(String email,String name,String imageUrl) {
	
	//Here write code for sending email using the template
	MimeMessage message = mailSender.createMimeMessage();
	try {
		InternetAddress fromAddress = new InternetAddress("synergisticit2020@gmail.com", "DesiBank PVT. LTD.");
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
			// DataSource fds = new FileDataSource("D:/sss.jpg");
			 System.out.println("_)#_)#  = URL = "+mail.getBaseUrl()+"/images/banklogo.png");
	         messageBodyPart.setDataHandler(new DataHandler(new URL(mail.getBaseUrl()+"/images/banklogo.png")));
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

