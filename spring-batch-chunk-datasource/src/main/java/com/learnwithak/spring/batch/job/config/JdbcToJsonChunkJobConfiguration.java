package com.learnwithak.spring.batch.job.config;

import com.learnwithak.spring.batch.job.model.json.StudentJson;
import com.learnwithak.spring.batch.job.processor.json.JsonItemProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class JdbcToJsonChunkJobConfiguration {
    public static final Logger LOG = LoggerFactory.getLogger(JdbcToJsonChunkJobConfiguration.class);
    @Autowired
    JdbcCursorItemReader<StudentJson> chunkReader;
    @Autowired
    JsonFileItemWriter<StudentJson> chunkWriter;
    @Autowired
    JsonItemProcessor chunkItemProcessor;

    public JdbcToJsonChunkJobConfiguration(JdbcCursorItemReader<StudentJson> chunkReader,
                                           JsonFileItemWriter<StudentJson> chunkWriter,
                                           JsonItemProcessor chunkItemProcessor) {
        this.chunkReader = chunkReader;
        this.chunkWriter = chunkWriter;
        this.chunkItemProcessor = chunkItemProcessor;
    }

    @Bean
    public Job jdbcToJsonChunkJob(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager){
        LOG.info("Calling JsonChunkJob method...");
        return new JobBuilder("JdbcToJsonChunkJob", jobRepository)
                .start(jdbcToJsonChunkJobStep(jobRepository,transactionManager))
                .build();
    }
    @Bean
    public Step jdbcToJsonChunkJobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        LOG.info("Calling JsonChunkStep method...");
        return new StepBuilder("chunkStep",jobRepository)
                .<StudentJson, StudentJson>chunk(3, transactionManager)
                .reader(chunkReader)
                .processor(chunkItemProcessor)
                .writer(chunkWriter)
                .build();
    }

}
