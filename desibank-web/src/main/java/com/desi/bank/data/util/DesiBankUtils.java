package com.desi.bank.data.util;

import javax.servlet.http.HttpServletRequest;

/**
 *  
 * @author VC1
 *  
 */
public class DesiBankUtils {
	public static String getServerBaseURL(HttpServletRequest request){
		    	String uri = request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() +       // "localhost"
	             ":" +                           // ":"
	             request.getServerPort() +       // "8080"
	             request.getContextPath();       // "/desi-bank"
		    	return uri;
	}
}
