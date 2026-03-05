package com.example.jobHunter.job.controller;


import com.example.jobHunter.Entity.Job;
import com.example.jobHunter.company.service.ICompanyService;
import com.example.jobHunter.dto.CompanyDto;
import com.example.jobHunter.dto.JobDto;
import com.example.jobHunter.job.Service.IJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {
    private final IJobService jobService;
    @GetMapping
    public ResponseEntity<List<JobDto>> getJobsByCompany
            ( @RequestParam(required = true) Long companyId)
    {
        List<JobDto> jobslist = jobService.getJobsByCompany(companyId);
        return ResponseEntity.ok().body(jobslist);
    }


}
