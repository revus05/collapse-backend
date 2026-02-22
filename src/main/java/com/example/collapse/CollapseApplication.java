package com.example.collapse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CollapseApplication {
	static void main(String[] args) {
		SpringApplication.run(CollapseApplication.class, args);
	}
}
