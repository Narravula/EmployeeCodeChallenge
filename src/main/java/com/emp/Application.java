package com.emp;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.emp.ctrl","com.emp.model","com.emp.service",
		"com.emp.repo","com.emp.wss","com.emp.swagger"})
public class Application {
	
	
	/**
	 * Main method to tickstart the Application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public Random random(){
		return new Random();
	} 

}
