package com.app.crudswaggerapi.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends AbstractCrudException {

    public BusinessException(final String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }

}