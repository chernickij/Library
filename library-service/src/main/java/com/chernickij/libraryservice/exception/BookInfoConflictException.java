package com.chernickij.libraryservice.exception;

public class BookInfoConflictException extends ApplicationException {

    public BookInfoConflictException(String msg){
        super(msg);
    }

    public BookInfoConflictException(String msg, Throwable cause){
        super(msg, cause);
    }
}
