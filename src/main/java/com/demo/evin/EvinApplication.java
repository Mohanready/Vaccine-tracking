package com.demo.evin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.evin.service","com.demo.evin.controller","com.demo.evin.config"})
@EntityScan("com.demo.evin.entity")
@EnableJpaRepositories("com.demo.evin.repository")
@EnableSwagger2
@EnableWebMvc
@Configuration
public class EvinApplication implements ApplicationListener<ApplicationReadyEvent>{

	public static void main(String[] args) {
		SpringApplication.run(EvinApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
