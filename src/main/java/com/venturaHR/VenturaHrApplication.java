package com.venturaHR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@EnableAutoConfiguration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class VenturaHrApplication {

	public static void main(String[] args) {
		SpringApplication.run(VenturaHrApplication.class, args);
	}

}