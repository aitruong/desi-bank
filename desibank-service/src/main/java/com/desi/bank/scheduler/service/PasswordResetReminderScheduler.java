package com.desi.bank.scheduler.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetReminderScheduler {
	
		//0 0 0 * * ? - every day 12 AM at night
	    //0 0 12 * * ?	- every day 12 PM 
	    //0 0 0,12 * * ?
		@Scheduled(cron = "*/60 * * * * ?")
		public void sendPasswordResetReminders() {
			System.out.println("current date = "+new Date());
			System.out.println(")#@)#)#)#)#)#)This is called everyone minutes");
			System.out.println(")#@)#)#)#)#)#)This is called everyone minutes");
			System.out.println(")#@)#)#)#)#)#)This is called everyone minutes");
			System.out.println(")#@)#)#)#)#)#)This is called everyone minutes");
		}

}
