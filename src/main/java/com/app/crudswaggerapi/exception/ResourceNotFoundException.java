package com.app.crudswaggerapi.exception;


import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AbstractCrudException {

    public ResourceNotFoundException(final String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }

}