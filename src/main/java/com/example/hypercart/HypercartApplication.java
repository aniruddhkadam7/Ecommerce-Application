package com.example.hypercart;

import com.example.hypercart.Model.Role;
import com.example.hypercart.Repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HypercartApplication {

	public static void main(String[] args) {
		SpringApplication.run(HypercartApplication.class, args);
	}

	@Bean         // its a beanddddssss
	CommandLineRunner init(RoleRepo roleRepo) {
		return args -> {
			if (roleRepo.findByName("USER") == null) {
				Role userRole = new Role("USER");
				roleRepo.save(userRole);
			}
		};
	}
}