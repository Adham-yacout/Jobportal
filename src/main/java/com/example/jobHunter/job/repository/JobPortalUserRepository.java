package com.example.jobHunter.job.repository;

import com.example.jobHunter.Entity.JobPortalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPortalUserRepository extends JpaRepository<JobPortalUser,Long> {
}
