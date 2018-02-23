package com.desi.bank.common.service.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;


@Service("LoginSuccessUserAuthHandler")
@Scope("singleton")
public class LoginSuccessUserAuthHandler  extends SimpleUrlAuthenticationSuccessHandler  {
	
	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(LoginSuccessUserAuthHandler.class);
	
	/**
	 * When you are coming to this handler  method
	 */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
		    response.setStatus(HttpServletResponse.SC_OK);
    	   PrintWriter printWriter=response.getWriter();
		   printWriter.write("ok");
		   printWriter.flush();
    		//you can find number of attempts from database
    }
}
            