package com.chernickij.library.exception;

public record ErrorMessage(int statusCode, String message, String description) {

}
