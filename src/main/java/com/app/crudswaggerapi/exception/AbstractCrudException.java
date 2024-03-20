package com.app.crudswaggerapi.exception;

public abstract class AbstractCrudException extends RuntimeException {

    private final Integer code;

    public AbstractCrudException(final String message, final Integer code) {
        super(message);
        this.code = code;
    }

    public AbstractCrudException(final String message, final Integer code, final Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

}