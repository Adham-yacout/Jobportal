package com.example.jobHunter.company.controller;


import com.example.jobHunter.dto.CompanyDto;
import com.example.jobHunter.company.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final ICompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        log.info("get all companies");
        List<CompanyDto> companyList = companyService.getAllCompanies();
        return ResponseEntity.ok().body(companyList);
    }
}
