package com.example.result_worker_service.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendResultEmail(String seatNo, String email, byte[] pdf) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("SPPU Result - " + seatNo);
       if(pdf != null){
           helper.setText("Dear Student,\n\nPlease find your SPPU result attached as a PDF.", false);
       }
       else{
           helper.setText("Dear Student,\n\nWe apologies for not getting your result from sppu server please try after some time.", false);
       }

        InputStreamSource attachment = new ByteArrayResource(pdf);
        helper.addAttachment("SPPU_Result_" + seatNo + ".pdf", attachment);

        mailSender.send(message);
    }
}

