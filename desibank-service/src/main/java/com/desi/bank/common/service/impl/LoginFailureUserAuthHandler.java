package com.desi.bank.common.service.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import com.desi.bank.common.service.impl.SpringSecurityService;


@Service("LoginFailureUserAuthHandler")
@Scope("singleton")
public class LoginFailureUserAuthHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	@Qualifier("SpringSecurityServiceImpl")
	private SpringSecurityService springSecurityService;
	
	
	/**
	 * When you are coming to this handler  method
	 */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception)
            throws IOException{
    		String username=request.getParameter("j_username");
    		int noOfAttempt=springSecurityService.findAttemptsCountForUser(username);
    			System.out.println("executing method  onAuthenticationFailure");
    			System.out.println("j_username = "+username);
    			System.out.println("noOfAttempt = "+noOfAttempt);
    		
    		//Hey I want to generate JSON response......................
    		response.setContentType("application/json");
    		 String jsonResponse="{}";
    		if(noOfAttempt==-1) {
    			///request.setAttribute("ApplicationMessage", "Sorry this user "+username+" does not exist");
    			 //super.getRedirectStrategy().sendRedirect(request, response, "/index.jsp?message=" + "Sorry this user "+username+" does not exist in our database");
    		     jsonResponse="{\"status\": \"0\", \"message\":\"Hey this user does not exit into our database\", \"noOfAttempt\" :0}";	
    		 	
    		}else{
    					//0
    					if(noOfAttempt<2)  {
    							int remainingAttempt=	3-(noOfAttempt+1);
    							springSecurityService.updateLoginAttempt(username, noOfAttempt+1);
    							jsonResponse="{\"status\": \"1\", \"message\":\"Username & password is not correct\", \"noOfAttempt\" :"+remainingAttempt+"}";
    					}else{
    							springSecurityService.lockUser(username);
    							jsonResponse="{\"status\": \"2\", \"message\":\"Sorry ! , you account has been block , please contact to bank.\", \"noOfAttempt\" :0}";	
    					}
    	 	 	   	// super.getRedirectStrategy().sendRedirect(request, response, "/index.jsp?message=Sorry your password is  not correct & remainingAttempt="+remainingAttempt);
    		}
    			System.out.println("jsonResponse  = "+jsonResponse);
 		   PrintWriter printWriter=response.getWriter();
		   printWriter.write(jsonResponse);
		   printWriter.flush();
    		//you can find number of attempts from database
    }
}
            