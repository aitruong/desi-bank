package com.desi.bank.common.advice;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogginWithAnnotationAdvice {
	
	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(LogginWithAnnotationAdvice.class);
	
	@After("@annotation(com.desi.bank.common.advice.MessageLogger)")
	public void  profiler(JoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		//this is the method detail which will be called after this advice executes
		Method method = signature.getMethod();
	    MessageLogger myAnnotation = method.getAnnotation(MessageLogger.class);
	    if(logger.isDebugEnabled()){
	    	logger.debug("####################executing method in service layer"+method.getName()+ "= ######################"+new Date());
	    	logger.debug("Method input in service layer = "+Arrays.asList(proceedingJoinPoint.getArgs()));
	    	logger.debug("#####################message from annotation = ######################"+myAnnotation.message());
	    }
		
	}
}
