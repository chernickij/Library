package com.chernickij.bookservice.service;

public interface LibraryProducerService {
    void sendBookCreationMessage(long bookId);
}
