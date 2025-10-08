package com.trex.order_service.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String customerEmail;
    private String subject;
    private String body;
}
