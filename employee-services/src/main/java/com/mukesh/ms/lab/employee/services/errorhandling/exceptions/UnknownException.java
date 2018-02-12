package com.mukesh.ms.lab.employee.services.errorhandling.exceptions;



import com.mukesh.ms.lab.employee.services.interfaces.IMessages;


public class UnknownException extends AbstractApplicationException {

    private final String message;

    public static final String MSG_ID = "EMP-RT-0002";

    public UnknownException(Throwable cause, final IMessages messages) {
        super(cause);
        this.message = messages.getMessage(MSG_ID);
    }

    @Override
    public String getMessage() {
        return message;
    }


    @Override
    public String getErrorCode() {
        return MSG_ID;
    }

}