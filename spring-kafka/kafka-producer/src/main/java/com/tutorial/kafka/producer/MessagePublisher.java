package com.tutorial.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

    @Autowired
    KafkaProducer kafkaProducer;

    @Scheduled(fixedDelay = 10000)
    public void sendMessage(){
        String message = "My message is cool !";
        kafkaProducer.sendMessage(message);
    }
}
