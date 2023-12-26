package com.chernickij.library.exception;

public class ResourceAlreadyExist extends ApplicationException {

    public ResourceAlreadyExist(String msg){
        super(msg);
    }

    public ResourceAlreadyExist(String msg, Throwable cause){
        super(msg, cause);
    }
}
