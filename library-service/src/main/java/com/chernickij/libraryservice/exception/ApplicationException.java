package com.chernickij.libraryservice.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException(String msg){
        super(msg);
    }

    public ApplicationException(String msg, Throwable cause){
        super(msg, cause);
    }
}
