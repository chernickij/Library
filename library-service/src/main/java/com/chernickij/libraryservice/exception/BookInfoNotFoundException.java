package com.chernickij.libraryservice.exception;

public class BookInfoNotFoundException extends ApplicationException {

    public BookInfoNotFoundException(String msg){
        super(msg);
    }

    public BookInfoNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
