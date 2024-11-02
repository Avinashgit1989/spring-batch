package com.learnwithak.spring.batch.config;

import com.learnwithak.spring.batch.tasklet.csv.CsvTasklet;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


/**This class is responsible to read the CSV file from resource destination and
 * process, write the csv item in another CSV file in targeted destination */
@Configuration
@AllArgsConstructor
public class CsvTaskLetJobConfig {
    @Autowired
    CsvTasklet csvTasklet;

    @Bean
    public Job csvTaskletJob(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager){
        return new JobBuilder("CsvTaskletJob", jobRepository)
                .start(csvTaskletStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step csvTaskletStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("CsvTaskletStep", jobRepository)
                .tasklet(csvTasklet,transactionManager)
                .build();
    }
}
