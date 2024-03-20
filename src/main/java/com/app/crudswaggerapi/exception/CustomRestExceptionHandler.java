package com.app.crudswaggerapi.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            BusinessException.class,
            ResourceNotFoundException.class
    })
    public ResponseEntity<Object> handleCrudException(final AbstractCrudException ex, final WebRequest request) {
        log.debug(String.format("Ocorreu um erro ao processar a requisição %s", request), ex);
        if (ex instanceof BusinessException) {
            return getResponseEntity(ex, BAD_REQUEST);
        } else if (ex instanceof ResourceNotFoundException) {
            return getResponseEntity(ex, NOT_FOUND);
        } else {
            return getResponseEntity(ex, INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Object> getResponseEntity(final AbstractCrudException ex, final HttpStatus status) {
        final ErrorResponse errorResponse = buildErrorResponse(ex, status);
        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(final AbstractCrudException exception, final HttpStatus status) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .httpStatus(status.value())
            .build();
    }
}