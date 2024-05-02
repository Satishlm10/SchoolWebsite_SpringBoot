package com.eazybytes.eazyschool.model;

import lombok.Data;

@Data
public class ContactDto {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;


}
