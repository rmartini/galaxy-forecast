package org.rmartini.galaxy;

import org.rmartini.galaxy.entity.Planet;
import org.rmartini.galaxy.repository.ForecastRepository;
import org.rmartini.galaxy.service.ForecastServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GalaxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxyApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ForecastRepository repository) {
		return args -> {
			Planet ferengi = new Planet(500, 1, 1);
			Planet betasoide = new Planet(2000, 3, 1);
			Planet vulcano = new Planet(1000, 5, -1);

			new ForecastServiceImpl(repository, ferengi, betasoide, vulcano);
		};
	}

}
