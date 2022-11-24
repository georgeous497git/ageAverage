package com.revisen.age.average;

import com.revisen.age.average.exception.AgeAverageException;
import com.revisen.age.average.service.AgeAverageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@Slf4j
@SpringBootApplication
public class AgeAverageApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AgeAverageApplication.class, args);
	}

	@Autowired
	AgeAverageService ageAverageService;

	@Override
	public void run(String... args) {
		log.info("Starting Application Age Average");
		try {
			ageAverageService.initApp();
		} catch (AgeAverageException e) {
			log.error("An unexpected error occurred during execution. Please contact 'Jorge Correa'", e);
		} finally {
			log.info("Ending Application Age Average");
		}
	}
}
