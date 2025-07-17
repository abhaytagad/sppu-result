package com.example.result_service.kafka;


import com.example.result_service.model.ResultRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ResultRequestProducer {

    private static final String TOPIC = "result-requests";

    @Autowired
    private KafkaTemplate<String, ResultRequest> kafkaTemplate;

    public void sendResultRequest(ResultRequest request) {
        kafkaTemplate.send(TOPIC, request);
    }
}

