package com.mukesh.ms.lab.employee.services.errorhandling.exceptions;

import com.mukesh.ms.lab.employee.services.interfaces.IMessages;

public class EntityKeyAlreadyExistsException extends AbstractApplicationException 
{
	 private final String message;


	    private static final String MSG_ID = "EMP-000011"; 

	   
		public EntityKeyAlreadyExistsException( final long ID, final Throwable cause,
	            final IMessages messages) {
	            super(cause);           
	            this.message = messages.getMessage(MSG_ID, Long.toString(ID));
	     }

	    

	    public String getMessage() {
	        return message;
	    }

	   
	    @Override
	    public String getErrorCode() {
	        return MSG_ID;
	    }
}
