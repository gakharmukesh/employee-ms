package com.mukesh.ms.lab.employee.resources.errorhandling.impl;

import org.springframework.http.HttpStatus;


import com.mukesh.ms.lab.employee.services.errorhandling.exceptions.EntityNotFoundException;
import com.mukesh.ms.lab.employee.services.errorhandling.exceptions.UnknownException;
import com.mukesh.ms.lab.employee.resources.errorhandling.exceptions.WebException;
import com.mukesh.ms.lab.employee.resources.errorhandling.interfaces.IResourceInterceptor;
import com.mukesh.ms.lab.employee.services.errorhandling.exceptions.AbstractApplicationException;
import com.mukesh.ms.lab.employee.services.errorhandling.exceptions.EntityKeyAlreadyExistsException;

public class ResourceInterceptor implements IResourceInterceptor {

	@Override
	public void handleAllErrors(Throwable ex) {
		// TODO Auto-generated method stub
		
		
		 if (ex instanceof AbstractApplicationException) 
		 {
			 AbstractApplicationException appEx = (AbstractApplicationException) ex;
			 
			 if (appEx instanceof EntityNotFoundException) {
	                throw new WebException(HttpStatus.NOT_FOUND, appEx.getErrorCode(), appEx.getMessage());
	         }else if(appEx instanceof EntityKeyAlreadyExistsException)
	         {
	        	 throw new WebException(HttpStatus.CONFLICT, appEx.getErrorCode(), appEx.getMessage());
	         }
			 else if(appEx instanceof UnknownException)
	         {
	        	 throw new WebException(HttpStatus.INTERNAL_SERVER_ERROR, appEx.getErrorCode(), appEx.getMessage());
	         }
			 
		 }else
		 {
			 throw new WebException(HttpStatus.INTERNAL_SERVER_ERROR, null, ex.getMessage());
		 }
	}

}
