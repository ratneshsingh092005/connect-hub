package com.ratnesh.connecthub.uploaderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ratnesh.connecthub")
public class UploaderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploaderServiceApplication.class, args);
	}

}
