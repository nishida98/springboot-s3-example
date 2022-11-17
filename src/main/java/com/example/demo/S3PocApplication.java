package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class S3PocApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3PocApplication.class, args);
	}


}
