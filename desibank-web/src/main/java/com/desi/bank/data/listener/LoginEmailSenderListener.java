package com.desi.bank.data.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

import com.desi.bank.constant.DesiBankConstant;
import com.spring.model.UserSessionVO;

public class LoginEmailSenderListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session=se.getSession();
		System.out.println("_______________________________________");
		System.out.println(")@((((((((Session session session session session((((((((((((");
		System.out.println(")@((((((((Session session session session session((((((((((((");
		System.out.println(")@((((((((Session session session session session((((((((((((");
		System.out.println("_______________________________________");
		//Write code to send email to admin
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session=se.getSession();
		//Write code to send email to admin
	}

}
