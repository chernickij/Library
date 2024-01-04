package com.chernickij.libraryservice.service.impl;

import com.chernickij.libraryservice.service.BookInfoListenerService;
import com.chernickij.libraryservice.service.BookInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookInfoListenerServiceImpl implements BookInfoListenerService {

    private final BookInfoService bookInfoService;

    @Override
    @KafkaListener(topics = "${message.topic.name}", groupId = "${message.group-id}")
    public void listenBookInfo(long bookId){
        log.debug("Executing the bookInfoListenerService.listenBookInfo method for book with id: %s".formatted(bookId));
        bookInfoService.save(bookId);
    }
}
