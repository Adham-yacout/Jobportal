package com.example.jobHunter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class JobHunterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobHunterApplication.class, args);
	}

}
