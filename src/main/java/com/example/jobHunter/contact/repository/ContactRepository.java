package com.example.jobHunter.contact.repository;

import com.example.jobHunter.Entity.Company;
import com.example.jobHunter.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findTopByOrderByCreatedAtDesc();
}
