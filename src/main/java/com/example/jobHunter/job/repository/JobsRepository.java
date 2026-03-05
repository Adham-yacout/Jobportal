package com.example.jobHunter.job.repository;

import com.example.jobHunter.Entity.Company;
import com.example.jobHunter.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobsRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompanyId(Long companyId);
}
