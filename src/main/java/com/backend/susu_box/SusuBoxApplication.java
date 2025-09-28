package com.backend.susu_box;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SusuBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SusuBoxApplication.class, args);
	}

}
