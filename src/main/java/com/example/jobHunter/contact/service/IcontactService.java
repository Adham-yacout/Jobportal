package com.example.jobHunter.contact.service;

import com.example.jobHunter.Entity.Contact;
import com.example.jobHunter.dto.ContactRequestDto;

import java.util.Optional;

public interface IcontactService {

    boolean saveContacts(ContactRequestDto contactRequestDto);
    ContactRequestDto getlatest();
}
