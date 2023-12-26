package com.chernickij.library.exception;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String msg){
        super(msg);
    }

    public ResourceNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
