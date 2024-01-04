package com.chernickij.libraryservice.exception;

public record ErrorMessage(int statusCode, String message, String description) {

}
