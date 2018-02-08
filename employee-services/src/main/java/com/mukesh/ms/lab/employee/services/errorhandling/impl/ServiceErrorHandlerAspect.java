package com.mukesh.ms.lab.employee.services.errorhandling.impl;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import com.mukesh.ms.lab.employee.services.errorhandling.interfaces.IServiceInterceptor;

@Aspect
public class ServiceErrorHandlerAspect 
{

	 @Inject
	 IServiceInterceptor serviceInterceptor;

	    @AfterThrowing(pointcut = "execution(* com.mukesh.ms.lab.employee.services.impl.EmployeeServiceImpl.*(..))", throwing = "ex")
	    public void handleErrors(JoinPoint jp,Exception ex)  {
	        serviceInterceptor.handleServiceErrors(jp,ex);
	    }
}
