package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.demo.model"}) // only needed if entities are not in a sub-package of the main application class
@EnableJpaRepositories(basePackages = {"com.example.demo.repository"}) // only needed if repositories are not in a sub-package of the main application class
public class TodoAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppBackendApplication.class, args);
	}
}
