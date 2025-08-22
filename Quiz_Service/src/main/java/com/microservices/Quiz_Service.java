package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Quiz_Service {

	public static void main(String[] args) {
		SpringApplication.run(Quiz_Service.class, args);
	}

}
