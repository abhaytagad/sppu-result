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

    // 🔹 New fields for captcha
    private String captchaText;   // user-entered captcha text
    private String orgCaptchaText;   // hash/code returned by backend

    public ResultRequest() {}
}
