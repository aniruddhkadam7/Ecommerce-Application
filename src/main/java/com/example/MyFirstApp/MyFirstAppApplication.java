package com.example.MyFirstApp;

import com.example.MyFirstApp.Model.Role;
import com.example.MyFirstApp.Repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyFirstAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepo roleRepo) {
		return args -> {
			if (roleRepo.findByName("USER") == null) {
				Role userRole = new Role("USER");
				roleRepo.save(userRole);
			}
		};
	}
}