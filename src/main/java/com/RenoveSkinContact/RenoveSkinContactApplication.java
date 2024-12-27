package com.RenoveSkinContact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = {"auth", "com.RenoveSkinContact.entities"})
public class RenoveSkinContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenoveSkinContactApplication.class, args);
	}

}
