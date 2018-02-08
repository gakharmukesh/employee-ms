package com.mukesh.ms.lab.employee.services.impl;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.mukesh.ms.lab.employee.services.interfaces.IMessages;

public class Messages implements IMessages {

    @Inject
    private MessageSource messageSource;

    @Override
    public String getMessage(String key, String... params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }

}
