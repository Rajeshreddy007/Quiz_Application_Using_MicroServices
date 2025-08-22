package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Service_Registry_Application {

	public static void main(String[] args) {
		SpringApplication.run(Service_Registry_Application.class, args);
	}

}
