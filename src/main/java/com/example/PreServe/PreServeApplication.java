package com.example.PreServe;

import com.example.PreServe.distance.DistanceCal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PreServeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreServeApplication.class, args);


	}

}
