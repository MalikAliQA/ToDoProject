package com.example.demo.config;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

	@Bean // managed object by spring
	// @Scope("prototype") //creational pattern
	public String time() {
		return "Hello, the current time is: " + LocalTime.now();
	}

	@Bean
	@Scope("prototype")
	public ModelMapper mapper() {
		return new ModelMapper();

	}

}
