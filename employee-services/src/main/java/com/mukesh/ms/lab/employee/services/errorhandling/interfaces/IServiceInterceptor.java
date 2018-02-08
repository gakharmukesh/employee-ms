package com.mukesh.ms.lab.employee.services.errorhandling.interfaces;

import org.aspectj.lang.JoinPoint;

public interface IServiceInterceptor 
{
	public void handleServiceErrors(JoinPoint jp,Throwable ex) ;
}
