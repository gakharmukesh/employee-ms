package com.mukesh.ms.lab.employee.services.errorhandling.exceptions;

import com.mukesh.ms.lab.employee.services.interfaces.IMessages;





  
public class EntityNotFoundException extends AbstractApplicationException  {

    private final String message;


    private static final String MSG_ID = "EMP-00001"; 

   
	public EntityNotFoundException( final String ID, final Throwable cause,
            final IMessages messages) {
            super(cause);           
            this.message = messages.getMessage(MSG_ID, ID);
     }

    

    public String getMessage() {
        return message;
    }

   
    @Override
    public String getErrorCode() {
        return MSG_ID;
    }



	
}
