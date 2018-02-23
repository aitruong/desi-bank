package com.desi.bank.common.advice;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service // 
@Aspect   //this class will work as a advice
public class TimeMessageLoggerAdvice {
	
	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(TimeMessageLoggerAdvice.class);
	
	@Around("execution(* com.desi.bank.customer.dao.impl.*.*(..))")
	public Object logTimeForCustomerDao(ProceedingJoinPoint  joinPoint) {
		String methodName=joinPoint.getSignature().getName();
		if(logger.isDebugEnabled()){
			logger.debug("@@@@@@@@method "+methodName+" is executed at "+new Date());
			logger.debug("Method input is "+Arrays.asList(joinPoint.getArgs()));
		}
		Object result=null;
		try {
			//This is actual method invocation
			result=joinPoint.proceed();
		}catch(Throwable ex){
			if(logger.isErrorEnabled()){
				logger.error("Exeception is ........................"+ex.getMessage());
			}	
		}finally {
				if(logger.isDebugEnabled()){
					logger.debug("@@@@@@@@method "+methodName+" is result is "+result);
				}
		}		
		return result;
	}
	
	
	/*@Before("execution(* com.desi.bank.admin.dao.impl.*.*(..))")
	public void logTimeForAdminDao(JoinPoint joinPoint) {
		String methodName=joinPoint.getSignature().getName();
		if(logger.isDebugEnabled()){
			logger.debug("@@@@@@@@method "+methodName+" is executed at "+new Date());
			logger.debug("Method input is "+Arrays.asList(joinPoint.getArgs()));
		}*/
	
	}

