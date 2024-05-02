package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.ContactDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ContactService {
    /*  boilerplate code of logging functionality instead use @Slf4j annotation provided by Lombok
    private static Logger log = LoggerFactory.getLogger(ContactService.class);
     */
    public void saveMessageDetails(ContactDto contact){
     log.info(contact.toString());
    }
}
