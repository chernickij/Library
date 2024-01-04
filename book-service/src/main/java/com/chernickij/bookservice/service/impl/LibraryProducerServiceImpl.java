package com.chernickij.bookservice.service.impl;

import com.chernickij.bookservice.service.LibraryProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LibraryProducerServiceImpl implements LibraryProducerService {
    @Value(value = "${message.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;

    @Override
    public void sendBookCreationMessage(long bookId) {
        kafkaTemplate.send(topicName, bookId);
    }
}
