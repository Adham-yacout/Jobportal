package com.example.jobHunter.job.repository;

import com.example.jobHunter.Entity.JobPortalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobPortalUserRepository extends JpaRepository<JobPortalUser,Long> {


    Optional<JobPortalUser> findJobPortalUserByEmail(String email);

}
