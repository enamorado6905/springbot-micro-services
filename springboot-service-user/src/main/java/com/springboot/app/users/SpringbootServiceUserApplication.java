package com.springboot.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceUserApplication.class, args);
	}

}
