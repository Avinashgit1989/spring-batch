package com.learnwithak.spring.batch.job.config;

import com.learnwithak.spring.batch.job.model.csv.StudentCsv;
import com.learnwithak.spring.batch.job.processor.csv.CsvItemProcessor;
import com.learnwithak.spring.batch.job.reader.csv.CsvItemReader;
import com.learnwithak.spring.batch.job.writter.csv.CsvItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CsvChunkJobConfiguration {
    @Autowired
    FlatFileItemReader<StudentCsv> chunkReader;
    @Autowired
    FlatFileItemWriter<StudentCsv> chunkWriter;
    @Autowired
    CsvItemProcessor chunkItemProcessor;

    public CsvChunkJobConfiguration(FlatFileItemReader<StudentCsv> chunkReader, FlatFileItemWriter<StudentCsv> chunkWriter, CsvItemProcessor chunkItemProcessor) {
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
                .<StudentCsv, StudentCsv>chunk(3, transactionManager)
                .reader(chunkReader)
                //.processor(chunkItemProcessor)
                .writer(chunkWriter)
                .build();
    }

}
