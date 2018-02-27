package org.email.message.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.desi.bank.email.service.TestVO;


@Service
public class MailConsumerServiceImpl implements ConsumerService {
	
	@Autowired
	@Qualifier("CustomerEmailService")
	private ICustomerEmailService customerEmailService;

	/**
	 * The name of the exchange.
	 */
	private static final String MAIL_HANDLER = "mail";

	/**
	 * The function that consumes messages from the broker(RabbitMQ)
	 * 
	 * @param data
	 */
	@Override
	@RabbitListener(bindings = @QueueBinding(value = @Queue(), 
			exchange = @Exchange(value = MAIL_HANDLER, type = ExchangeTypes.FANOUT)))
	public void consumerMessage(byte[] data) {
		TestVO userMstr;
		try {
			userMstr = (TestVO) Utils.toObject(data);
			   try {
					EmailVO mail=new EmailVO();
					mail.setBaseUrl("NA");
					mail.setFrom("desibankproject@gmail.com");
					mail.setLink("http://www.gmail.com");
					mail.setName(userMstr.getName());
					mail.setSubject("Regarding Registration link to open a saving account");
					mail.setTo(userMstr.getTo());
					customerEmailService.sendRegistrationEmail(mail);
		}catch(Exception ex) {
			StringWriter stringWriter=new StringWriter();
			ex.printStackTrace(new PrintWriter(stringWriter));
		}
			
			
			System.out.println("Message coming from sender is.............................." + userMstr.toString() + "'");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
