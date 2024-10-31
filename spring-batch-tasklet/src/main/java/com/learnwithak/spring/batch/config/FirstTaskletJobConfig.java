package com.learnwithak.spring.batch.config;

import com.learnwithak.spring.batch.processor.FirstItemProcessor;
import com.learnwithak.spring.batch.reader.FirstReader;
import com.learnwithak.spring.batch.tasklet.FirstTaskLet;
import com.learnwithak.spring.batch.writer.FirstWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class FirstTaskletJobConfig {
    @Autowired
    FirstReader firstReader;
    @Autowired
    FirstWriter firstWriter;
    @Autowired
    FirstItemProcessor firstItemProcessor;
    @Autowired
    FirstTaskLet firstTaskLet;

    @Bean
    public Job firstJob(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager){
        return new JobBuilder("FirstJob", jobRepository)
                .start(firstChunkJobStep(jobRepository,transactionManager))
                .start(firstTaskletStep(jobRepository, transactionManager))
                .build();
    }
    @Bean
    public Step firstChunkJobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("firstStep",jobRepository)
                .<Integer, Long>chunk(3, transactionManager)
                .reader(firstReader)
                .processor(firstItemProcessor)
                .writer(firstWriter)
                .build();
    }


    private Step firstTaskletStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("firstTaskLetStep", jobRepository)
                .tasklet(firstTaskLet, transactionManager)
                .build();
    }
}
