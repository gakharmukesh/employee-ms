package com.mukesh.ms.lab.employee.resources.errorhandling.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import com.mukesh.ms.lab.employee.resources.errorhandling.exceptions.WebException;
import com.mukesh.ms.lab.employee.resources.models.ErrorResponse;




@ControllerAdvice
public class ExceptionToErrorResponseMapper extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionToErrorResponseMapper.class);
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, Exception exception) {

    	logger.info("Error"+exception);
    	
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Internal Server Error";
        String code = "";

        WebException webEx = null;
        if (exception instanceof WebException) {
            webEx = (WebException) exception;
            status = webEx.getStatus();
            message = webEx.getMessage();
            code = webEx.getErrorCode();
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setLink(req.getServletPath());
        errorResponse.setCode(code);
        errorResponse.setMessage(message);
        errorResponse.traceId(req.getHeader("ConversationId"));

        return new ResponseEntity<>(errorResponse, status);
    }

}
