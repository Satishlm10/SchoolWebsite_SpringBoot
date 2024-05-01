package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.ContactDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    public void saveMessageDetails(ContactDto contact){
     log.info(contact.toString());
    }
}
