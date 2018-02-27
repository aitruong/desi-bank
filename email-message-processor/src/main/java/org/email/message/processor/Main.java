package org.email.message.processor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main   {
		public static void main(String[] args) throws InterruptedException {
			   ApplicationContext applicationContext=new ClassPathXmlApplicationContext("email-message-sender.xml");
			   while(true) {
				   	   System.out.println("_---------------consumer is up and running..............................");
				   	    Thread.sleep(1000000);
			   }
		}
}
