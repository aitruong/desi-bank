package com.desi.bank.exception.advice;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.desi.bank.exception.DesiBankException;

/**
 * 
 * @author nagendra.yadav
 * 
 */

@Aspect
//this class will act as an advice
//so we are using @Aspect annotation
@Component
public class DesiBankExceptionAdvice {
	
	public DesiBankExceptionAdvice(){
		System.out.println("_______EZLOAN______________");
		System.out.println("_______EZLOAN______________");
	}

	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DesiBankExceptionAdvice.class);

	/**
	   * Called between the throw and the catch
	   */
	  @AfterThrowing(pointcut = "execution(* com.spring.dao.impl.*.*(..))", throwing = "e")
	  public void adviceArroundDAO(JoinPoint joinPoint, Throwable e) {
	    Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    logger.info("Write something in the log... We have caught exception in method: "
	        + methodName + " with arguments "
	        + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
	        + e.getMessage(), e);
	    DesiBankException  bankException=new DesiBankException("Problem occurs in in database Layer");
	    bankException.initCause(e);
	    throw bankException;
	  }
	  
	  /**
	   * Called between the throw and the catch
	   */
	  @AfterThrowing(pointcut = "execution(* com.spring.service.impl.*.*(..))", throwing = "e")
	  public void serviceLayerException(JoinPoint joinPoint, Throwable e) {
	    Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    if(logger.isErrorEnabled())
	    logger.error("Write something in the log... We have caught exception in method: "
	        + methodName + " with arguments "
	        + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
	        + e.getMessage(), e);
	    DesiBankException  bankException=new DesiBankException("Problem occurs in in service Layer");
	    bankException.initCause(e);
	    throw bankException;
	  }
	  

	
}
