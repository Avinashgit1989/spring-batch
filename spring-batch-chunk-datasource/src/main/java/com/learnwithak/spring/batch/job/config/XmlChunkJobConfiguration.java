package com.learnwithak.spring.batch.job.config;

import com.learnwithak.spring.batch.job.model.json.StudentJson;
import com.learnwithak.spring.batch.job.model.xml.StudentXml;
import com.learnwithak.spring.batch.job.processor.csv.CsvItemProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class XmlChunkJobConfiguration {
    public static final Logger LOG = LoggerFactory.getLogger(XmlChunkJobConfiguration.class);
    @Autowired
    StaxEventItemReader<StudentXml> chunkReader;
    @Autowired
    StaxEventItemWriter<StudentXml> chunkWriter;
    @Autowired
    CsvItemProcessor chunkItemProcessor;

    public XmlChunkJobConfiguration(StaxEventItemReader<StudentXml> chunkReader, StaxEventItemWriter<StudentXml> chunkWriter, CsvItemProcessor chunkItemProcessor) {
        this.chunkReader = chunkReader;
        this.chunkWriter = chunkWriter;
        this.chunkItemProcessor = chunkItemProcessor;
    }

    @Bean
    public Job xmlChunkjob(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager){
        LOG.info("Calling XMLChunkJob method...");
        return new JobBuilder("XmlChunkJob", jobRepository)
                .start(xmlChunkJobStep(jobRepository,transactionManager))
                .build();
    }
    @Bean
    public Step xmlChunkJobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        LOG.info("Calling XmlChunkStep method...");
        return new StepBuilder("chunkStep",jobRepository)
                .<StudentXml, StudentXml >chunk(3, transactionManager)
                .reader(chunkReader)
                //.processor(chunkItemProcessor)
                .writer(chunkWriter)
                .build();
    }



}


