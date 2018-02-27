package com.desi.bank.email.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service("EmailMessageSenderService")
public class EmailMessageSenderService {
	
    /**
     *  The name of the Exchange
     */
    private static final String MAIL_HANDLER = "mail";

    public void sendSampleData(TestVO testVO) {
    	 try {
             ConnectionFactory factory = new ConnectionFactory();
             factory.setHost("localhost");
             Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
             channel.exchangeDeclare(MAIL_HANDLER, "fanout");
            channel.basicPublish(MAIL_HANDLER, "", null, Utils.toByteArray(testVO));
             System.out.println("Message has been send thank you  = "+testVO);
             channel.close();
             connection.close();
         } catch (IOException io) {
             System.out.println("IOException");
             io.printStackTrace();
         } catch (TimeoutException toe) {
             System.out.println("TimeoutException : " + toe.getMessage());
             toe.printStackTrace();
         }
		
	}

}
