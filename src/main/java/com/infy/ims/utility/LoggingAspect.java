package com.infy.ims.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;

public class LoggingAspect {
	
	private static final Log LOGGER=LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.infy.ims.service.*Impl.*(..))", throwing = "exception")	
	public void logExceptionFromService(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
	}

}
