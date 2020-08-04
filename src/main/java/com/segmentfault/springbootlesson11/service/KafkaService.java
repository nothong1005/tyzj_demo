package com.segmentfault.springbootlesson11.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 62667
 */
@Service
public class KafkaService {
    private final KafkaTemplate<String, Object> kafkaTemplate;


    public KafkaService(KafkaTemplate<String,Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String topic, Object object) {
        kafkaTemplate.send(topic, object);
    }

}
