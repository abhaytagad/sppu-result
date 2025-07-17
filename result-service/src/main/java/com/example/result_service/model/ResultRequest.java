package com.example.result_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ResultRequest {

    private String seatNo;
    private String motherName;
    private String patternName;
    private String patternID;
    private String email;

    public ResultRequest() {}

}
