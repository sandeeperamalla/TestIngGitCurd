package com.example.studentservice.studentconfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
