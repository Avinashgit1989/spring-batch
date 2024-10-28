package com.learnwithak.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({
		"com.learnwithak.spring.batch.job.*"})
@EnableScheduling
@EnableAsync
public class SpringBatchChunkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchChunkApplication.class, args);
	}

}
