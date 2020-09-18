package com.hardik.bhagvadgita;

import java.io.IOException;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BhagvadGitaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BhagvadGitaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
