package org.email.message.processor;

import java.io.IOException;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.desi.bank.email.service.TestVO;


@Service
public class MailConsumerServiceImpl implements ConsumerService {

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
