package com.learnwithak.spring.batch.config;

import com.learnwithak.spring.batch.tasklet.json.JsonTasklet;
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

/**This class is responsible to read the Json file from resource destination and
 * process, write the csv item in another Json file in targeted destination */
@Configuration
@AllArgsConstructor
public class JsonTaskletJobConfig {
    @Autowired
    JsonTasklet jsonTasklet;

    @Bean
    public Job jsonTaskletJob(JobRepository jobRepository,
                             PlatformTransactionManager transactionManager){
        return new JobBuilder("JsonTaskletJob", jobRepository)
                .start(jsonTaskletStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step jsonTaskletStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("JsonTaskletStep", jobRepository)
                .tasklet(jsonTasklet,transactionManager)
                .build();
    }
}

