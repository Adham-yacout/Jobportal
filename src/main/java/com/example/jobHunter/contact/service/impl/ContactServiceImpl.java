package com.example.jobHunter.contact.service.impl;

import com.example.jobHunter.Entity.Contact;
import com.example.jobHunter.contact.repository.ContactRepository;
import com.example.jobHunter.contact.service.IcontactService;
import com.example.jobHunter.dto.ContactRequestDto;
import com.example.jobHunter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IcontactService {

    private  final ContactRepository contactRepository;

    @Override
    public boolean saveContacts(ContactRequestDto contactRequestDto) {
            boolean result=false;

        Contact contact = contactRepository.save(Transformtocontact(contactRequestDto));
        if (contact !=null && contact.getId()!=null){
            contactRepository.save(contact);
            result=true;
        }
        return result;
    }

    @Override
    public ContactRequestDto getlatest() {
        Contact contact = contactRepository
                .findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No contacts found"));
return transformToDto(contact);

    }

    public Contact Transformtocontact(ContactRequestDto contactRequestDto)
    {
        Contact contact= new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
        contact.setStatus("NEW");
        return  contact;
    }

    private ContactRequestDto transformToDto(Contact contact) {

        return new ContactRequestDto(
                contact.getEmail(),
                contact.getMessage(),
                contact.getName(),
                contact.getSubject(),
                contact.getUserType()
        );
    }

}

