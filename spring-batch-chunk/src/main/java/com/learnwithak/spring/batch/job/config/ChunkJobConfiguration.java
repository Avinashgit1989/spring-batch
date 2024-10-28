package com.learnwithak.spring.batch.job.config;

import com.learnwithak.spring.batch.job.processor.ArrayItemProcessor;
import com.learnwithak.spring.batch.job.reader.ArrayListReader;
import com.learnwithak.spring.batch.job.writter.ArrayItemWriter;
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
public class ChunkJobConfiguration {
    @Autowired
    ArrayListReader chunkReader;
    @Autowired
    ArrayItemWriter chunkWriter;
    @Autowired
    ArrayItemProcessor chunkItemProcessor;

    public ChunkJobConfiguration(ArrayListReader chunkReader, ArrayItemWriter chunkWriter, ArrayItemProcessor chunkItemProcessor) {
        this.chunkReader = chunkReader;
        this.chunkWriter = chunkWriter;
        this.chunkItemProcessor = chunkItemProcessor;
    }

    @Bean
    public Job myChunkjob(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager){
        return new JobBuilder("MyChunkJob", jobRepository)
                .start(chunkJobStep(jobRepository,transactionManager))
                .build();
    }
    @Bean
    public Step chunkJobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("chunkStep",jobRepository)
                .<Integer, Long>chunk(3, transactionManager)
                .reader(chunkReader)
                .processor(chunkItemProcessor)
                .writer(chunkWriter)
                .build();
    }

}
