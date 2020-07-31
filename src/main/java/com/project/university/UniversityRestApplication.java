package com.project.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.project.university")
public class UniversityRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityRestApplication.class, args);
	}
}
