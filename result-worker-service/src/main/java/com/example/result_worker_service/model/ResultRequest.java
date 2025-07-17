package com.example.result_worker_service.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResultRequest {

    private String seatNo;
    private String motherName;
    private String patternName;
    private String patternID;
    private String email;


    public ResultRequest() {}


}
