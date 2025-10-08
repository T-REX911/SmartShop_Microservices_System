package com.trex.email_service.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trex.email_service.dto.EmailRequest;
import com.trex.email_service.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "email-queue")
    public void consumeMessageFromQueue(String email) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        EmailRequest emailRequest = mapper.readValue(email,EmailRequest.class);


        emailService.sendEmail(emailRequest.getCustomerEmail(),emailRequest.getBody(),emailRequest.getSubject());


    }
}
