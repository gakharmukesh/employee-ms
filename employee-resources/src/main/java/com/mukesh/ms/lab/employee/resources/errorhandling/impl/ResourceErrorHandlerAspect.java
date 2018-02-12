package com.mukesh.ms.lab.employee.resources.errorhandling.impl;

import javax.inject.Inject;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;


import com.mukesh.ms.lab.employee.resources.errorhandling.interfaces.IResourceInterceptor;


@Aspect
public class ResourceErrorHandlerAspect 
{

	 @Inject
	 IResourceInterceptor resourceInterceptor;

	    @AfterThrowing(pointcut = "execution(* com.mukesh.ms.lab.employee.resources.impl.EmployeesApiImpl.*(..))", throwing = "ex")
	    public void handleAllErrors(Throwable ex) {
	        resourceInterceptor.handleAllErrors(ex);
	    }
}
