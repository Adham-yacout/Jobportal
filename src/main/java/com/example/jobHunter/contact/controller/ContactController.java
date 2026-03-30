package com.example.jobHunter.contact.controller;


import com.example.jobHunter.contact.service.IcontactService;
import com.example.jobHunter.dto.ContactRequestDto;
import com.example.jobHunter.dto.ContactResponseDto;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IcontactService icontactService;

    @PostMapping("/save")
    public ResponseEntity<String> savecontact(@RequestBody  @Valid ContactRequestDto contactRequestDto) {
   boolean issaved= icontactService.saveContacts(contactRequestDto);
        System.out.println(issaved);
        System.out.println(contactRequestDto);
    if(issaved)
    {
        return  ResponseEntity.status(HttpStatus.CREATED).body("Request processed successfully");
    }
    else{
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request processing failed");
    }
    }

    @GetMapping("/latest")
    public  ResponseEntity<?> returnlatestcontact(){
        ContactRequestDto contactRequestDto = icontactService.getlatest();
        return ResponseEntity.ok(contactRequestDto);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ContactResponseDto>> fetchNewContactMsgs() {
        List<ContactResponseDto> contactResponseDtos = icontactService.fetchNewContactMsgs();
        return ResponseEntity.status(HttpStatus.OK).body(contactResponseDtos);
    }

    @GetMapping("/page/admin")
    public ResponseEntity<Page<ContactResponseDto>> fetchNewContactMsgsWithPaginationAndSort(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<ContactResponseDto> contactResponseDtoPage = icontactService
                .fetchNewContactMsgsWithPaginationAndSort(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.status(HttpStatus.OK).body(contactResponseDtoPage);
    }
}
