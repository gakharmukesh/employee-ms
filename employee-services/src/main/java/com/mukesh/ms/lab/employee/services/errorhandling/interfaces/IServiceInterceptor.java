package com.mukesh.ms.lab.employee.services.errorhandling.interfaces;

import org.aspectj.lang.JoinPoint;

public interface IServiceInterceptor 
{
	 void handleServiceErrors(JoinPoint jp,Throwable ex) ;
}
