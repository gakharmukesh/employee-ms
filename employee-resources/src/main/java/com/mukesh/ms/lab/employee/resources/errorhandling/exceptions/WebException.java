package com.mukesh.ms.lab.employee.resources.errorhandling.exceptions;

import org.springframework.http.HttpStatus;

public class WebException extends RuntimeException 
{
	   private final HttpStatus status;
	   private final String errorCode;
	   private final String message;

	    public WebException(final HttpStatus status, final String code, final String message) {
	        this.status = status;
	        this.errorCode = code;
	        this.message = message;
	    }

	    public HttpStatus getStatus() {
	        return status;
	    }

	    public String getErrorCode() {
	        return errorCode;
	    }

	    @Override
	    public String getMessage() {
	        return message;
	    }
}
