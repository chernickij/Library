package com.chernickij.libraryservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BookInfoNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage bookInfoNotFoundException(BookInfoNotFoundException ex, WebRequest request) {
        log.error("Exception" + ex);
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(BookInfoConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage bookInfoConflictException(BookInfoConflictException ex, WebRequest request) {
        log.error("Exception" + ex);
        return new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
        log.error("Exception" + ex);
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false));
    }
}
