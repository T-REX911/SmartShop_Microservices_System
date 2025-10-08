package com.trex.email_service.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String recipient;
    private String body;
    private String subject;
}
