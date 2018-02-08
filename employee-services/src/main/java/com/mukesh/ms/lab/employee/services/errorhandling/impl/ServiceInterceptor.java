package com.mukesh.ms.lab.employee.services.errorhandling.impl;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.aspectj.lang.JoinPoint;
import org.springframework.dao.DataIntegrityViolationException;

import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;
import com.mukesh.ms.lab.employee.services.errorhandling.exceptions.UnknownException;
import com.mukesh.ms.lab.employee.services.errorhandling.interfaces.IServiceInterceptor;
import com.mukesh.ms.lab.employee.services.interfaces.IMessages;

public class ServiceInterceptor implements IServiceInterceptor{

	@Inject
	private IMessages messages;
	@Override
	public void handleServiceErrors(JoinPoint jp,Throwable ex) {
		
		if(ex instanceof EntityNotFoundException)
		{
			Object[] object=jp.getArgs();
			 String id=null;
			if(object.length>0)
			{
			    id=Long.toString((Long)object[0]);
			  
			}
			throw new com.mukesh.ms.lab.employee.services.errorhandling.exceptions.EntityNotFoundException(id,ex,messages);
		}else if(ex instanceof DataIntegrityViolationException)
		{
			Object[] object=jp.getArgs();
			EmployeeDTO dto=null;
			if(object.length>0)
			{
			    dto=(EmployeeDTO)object[0];
			    throw new com.mukesh.ms.lab.employee.services.errorhandling.exceptions.EntityKeyAlreadyExistsException(dto.getId(),ex,messages);
			}	
		}else
		{
			throw new UnknownException(ex,messages);
		}
	}

}
