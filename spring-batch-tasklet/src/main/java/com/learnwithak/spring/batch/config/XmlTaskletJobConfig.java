package com.learnwithak.spring.batch.config;

import com.learnwithak.spring.batch.tasklet.xml.XmlTasklet;
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

/**This class is responsible to read the Xml file from resource destination and
 * process, write the csv item in another Xml file in targeted destination */
@Configuration
@AllArgsConstructor
public class XmlTaskletJobConfig {
    @Autowired
    XmlTasklet xmlTasklet;

    @Bean
    public Job xmlTaskletJob(JobRepository jobRepository,
                             PlatformTransactionManager transactionManager){
        return new JobBuilder("XmlTaskletJob", jobRepository)
                .start(xmlTaskletStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step xmlTaskletStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("XmlTaskletStep", jobRepository)
                .tasklet(xmlTasklet,transactionManager)
                .build();
    }
}

