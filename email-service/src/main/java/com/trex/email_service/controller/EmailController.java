package com.trex.email_service.controller;

import com.trex.email_service.dto.EmailDto;
import com.trex.email_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailDto dto){
        emailService.sendEmail(dto.getRecipient(),dto.getBody(), dto.getSubject());
    }
}
