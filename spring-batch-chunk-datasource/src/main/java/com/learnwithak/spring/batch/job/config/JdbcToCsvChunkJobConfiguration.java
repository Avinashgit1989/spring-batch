package com.learnwithak.spring.batch.job.config;

import com.learnwithak.spring.batch.job.model.csv.StudentCsv;
import com.learnwithak.spring.batch.job.processor.csv.CsvItemProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JdbcToCsvChunkJobConfiguration {
    public static final Logger LOG = LoggerFactory.getLogger(JdbcToCsvChunkJobConfiguration.class);
    @Autowired
    JdbcCursorItemReader<StudentCsv> chunkReader;
    @Autowired
    FlatFileItemWriter<StudentCsv> chunkWriter;
    @Autowired
    CsvItemProcessor chunkItemProcessor;

    public JdbcToCsvChunkJobConfiguration(JdbcCursorItemReader<StudentCsv> chunkReader,
                                          FlatFileItemWriter<StudentCsv> chunkWriter,
                                          CsvItemProcessor chunkItemProcessor) {
        this.chunkReader = chunkReader;
        this.chunkWriter = chunkWriter;
        this.chunkItemProcessor = chunkItemProcessor;
    }

    @Bean
    public Job jdbcToCsvChunkJob(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager){
        LOG.info("CSV Job is being called...");
        return new JobBuilder("JdbcToCsvChunkJob", jobRepository)
                .start(jdbcCsvChunkJobStep(jobRepository,transactionManager))
                .build();
    }
    @Bean
    public Step jdbcCsvChunkJobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        LOG.info("CSV Steps is being called...");
        return new StepBuilder("chunkStep",jobRepository)
                .<StudentCsv, StudentCsv>chunk(3, transactionManager)
                .reader(chunkReader)
                .processor(chunkItemProcessor)
                .writer(chunkWriter)
                .build();
    }

}
