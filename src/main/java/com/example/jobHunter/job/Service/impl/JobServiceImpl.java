package com.example.jobHunter.job.Service.impl;

import com.example.jobHunter.Entity.Job;
import com.example.jobHunter.dto.JobDto;
import com.example.jobHunter.job.Service.IJobService;
import com.example.jobHunter.job.repository.JobsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements IJobService {
    private final JobsRepository jobsRepository;

    @Override
    public List<JobDto> getJobsByCompany(Long companyid) {
        List<Job> jobs=jobsRepository.findByCompanyId(companyid);
        return  jobs.stream().map(this::transformToDto).collect(Collectors.toList());

    }

    private JobDto transformToDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getCompany().getId(),
                job.getCompany().getName(),
                job.getCompany().getLogo(),
                job.getLocation(),
                job.getWorkType(),
                job.getJobType(),
                job.getCategory(),
                job.getExperienceLevel(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getSalaryCurrency(),
                job.getSalaryPeriod(),
                job.getDescription(),
                job.getRequirements(),
                job.getBenefits(),
                job.getPostedDate(),
                job.getApplicationDeadline(),
                job.getApplicationsCount(),
                job.getFeatured(),
                job.getUrgent(),
                job.getRemote(),
                job.getStatus()
        );
    }
}
