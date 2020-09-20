package com.football.tournament;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FootballTournamentApplication {

	@RequestMapping("/")
	public String home() {
		return "Welcome to Football League Tournament";
	}

	public static void main(String[] args) {
		SpringApplication.run(FootballTournamentApplication.class, args);
	}

}
