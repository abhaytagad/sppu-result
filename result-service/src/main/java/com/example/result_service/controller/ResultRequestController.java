package com.example.result_service.controller;

import com.example.result_service.kafka.ResultRequestProducer;
import com.example.result_service.model.ResultRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/result")
@CrossOrigin(origins = "*")
public class ResultRequestController {

    @Autowired
    private ResultRequestProducer producer;

    @PostMapping(value = "/request", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String requestResult(@RequestBody ResultRequest resultRequest
    ) {
        System.out.println(resultRequest.getEmail());
        System.out.println(resultRequest.getSeatNo());
        System.out.println(resultRequest.getCaptchaText());
        System.out.println(resultRequest.getMotherName());
        System.out.println(resultRequest.getOrgCaptchaText());
        System.out.println(resultRequest.getPatternID());
        

        producer.sendResultRequest(resultRequest);

        return "Result request received. Processing...";
    }
}
