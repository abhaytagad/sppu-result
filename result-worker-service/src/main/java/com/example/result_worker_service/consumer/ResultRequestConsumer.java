package com.example.result_worker_service.consumer;



import com.example.result_worker_service.model.ResultRequest;
import com.example.result_worker_service.service.ResultFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class ResultRequestConsumer {

    @Autowired
    private ResultFetcherService fetcherService;



    @KafkaListener(topics = "result-requests", groupId = "result-group")
    public void consume(ResultRequest request)  {

        System.out.println("📩 Received request for: "+request.getSeatNo());
        try{
            fetcherService.fetchResult(request);

        }
        catch(Exception e){
            System.out.println("<UNK> Error: " + e.getMessage());
        }
    }

}

