package com.api.movietribute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.api.movietribute.models.VaultConfiguration;

@SpringBootApplication
public class MovietributeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovietributeApplication.class, args);
	}

}
