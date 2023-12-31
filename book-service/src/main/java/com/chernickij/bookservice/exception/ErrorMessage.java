package com.chernickij.bookservice.exception;

public record ErrorMessage(int statusCode, String message, String description) {

}
