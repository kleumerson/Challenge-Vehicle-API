package com.ags.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages={
		"com.ags.backend.repository.brand",
		"com.ags.backend.repository.fuel",
		"com.ags.backend.repository.model",
		"com.ags.backend.repository.vehicle"
})

@EntityScan(basePackages={
		"com.ags.backend.controller",
		"com.ags.backend.dto",
		"com.ags.backend.entity",
		"com.ags.backend.repository",
		"com.ags.backend.service"
})

@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
