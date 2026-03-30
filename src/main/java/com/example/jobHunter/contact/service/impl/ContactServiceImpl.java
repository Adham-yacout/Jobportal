package com.example.jobHunter.contact.service.impl;

import com.example.jobHunter.Entity.Contact;
import com.example.jobHunter.constant.ApplicationConstants;
import com.example.jobHunter.contact.repository.ContactRepository;
import com.example.jobHunter.contact.service.IcontactService;
import com.example.jobHunter.dto.ContactRequestDto;
import com.example.jobHunter.dto.ContactResponseDto;
import com.example.jobHunter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    @Override
    public List<ContactResponseDto> fetchNewContactMsgs() {
        List<Contact> contacts = contactRepository.findContactsByStatusOrderByCreatedAtAsc(ApplicationConstants.NEW_MESSAGE);
        List<ContactResponseDto> responseDtos = contacts.stream()
                .map(this::transformToContactResponseDto)
                .collect(Collectors.toList());
        return responseDtos;
    }

    private ContactResponseDto transformToContactResponseDto(Contact contact) {

        return new ContactResponseDto(
               contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getUserType(),
                contact.getSubject(),
                contact.getMessage(),
                contact.getStatus(),
                contact.getCreatedAt()
        );
    }

    @Override
    public Page<ContactResponseDto> fetchNewContactMsgsWithPaginationAndSort(
            int pageNumber, int pageSize, String sortBy, String sortDir) {
        // Create Sort object based on sortBy and sortDir parameters
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        // Create Pageable object with page number, page size, and sorting
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        // Fetch paginated and sorted contacts from repository
        Page<Contact> contactPage = contactRepository.findContactsByStatus(
                ApplicationConstants.NEW_MESSAGE, pageable);

        // Transform Contact entities to ContactResponseDto
        Page<ContactResponseDto> responseDtoPage = contactPage.map(this::transformToContactResponseDto);
        return responseDtoPage;
    }



}

