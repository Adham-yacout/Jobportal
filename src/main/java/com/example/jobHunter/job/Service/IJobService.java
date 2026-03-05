package com.example.jobHunter.job.Service;

import com.example.jobHunter.dto.JobDto;

import java.util.List;

public interface IJobService {

    List<JobDto> getJobsByCompany(Long companyid);
}
