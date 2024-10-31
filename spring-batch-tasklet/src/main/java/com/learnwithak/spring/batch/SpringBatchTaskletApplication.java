package com.learnwithak.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableAsync
@ComponentScan({
		"com.learnwithak.spring.batch.*"})
@EnableScheduling
public class SpringBatchTaskletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchTaskletApplication.class, args);
	}

}
