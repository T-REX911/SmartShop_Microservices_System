package com.trex.email_service.dto;

import lombok.Data;

@Data
public class EmailRequest {
    private String customerEmail;
    private String subject;
    private String body;
}
