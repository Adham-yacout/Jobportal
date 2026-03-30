package com.example.jobHunter.contact.service;

import com.example.jobHunter.Entity.Contact;
import com.example.jobHunter.dto.ContactRequestDto;
import com.example.jobHunter.dto.ContactResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IcontactService {

    boolean saveContacts(ContactRequestDto contactRequestDto);
    ContactRequestDto getlatest();

    List<ContactResponseDto> fetchNewContactMsgs();
    Page<ContactResponseDto> fetchNewContactMsgsWithPaginationAndSort(int pageNumber, int pageSize,
                                                                      String sortBy, String sortDir);

}
