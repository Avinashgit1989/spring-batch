package com.learnwithak.spring.batch.job.config;

import com.learnwithak.spring.batch.job.model.xml.StudentXml;
import com.learnwithak.spring.batch.job.processor.xml.XmlItemProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class XmlToJdbcChunkJobConfiguration {
    public static final Logger LOG = LoggerFactory.getLogger(XmlToJdbcChunkJobConfiguration.class);
    @Autowired
    StaxEventItemReader<StudentXml> chunkReader;
    @Autowired
    JdbcBatchItemWriter<StudentXml> chunkWriter;
    @Autowired
    XmlItemProcessor chunkItemProcessor;

    public XmlToJdbcChunkJobConfiguration(StaxEventItemReader<StudentXml> chunkReader,
                                          JdbcBatchItemWriter<StudentXml> chunkWriter,
                                          XmlItemProcessor chunkItemProcessor) {
        this.chunkReader = chunkReader;
        this.chunkWriter = chunkWriter;
        this.chunkItemProcessor = chunkItemProcessor;
    }

    @Bean
    public Job xmlToJdbcChunkjob(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager){
        LOG.info("Calling XMLChunkJob method...");
        return new JobBuilder("XmlToJdbcChunkJob", jobRepository)
                .start(xmlToJdbcChunkJobStep(jobRepository,transactionManager))
                .build();
    }
    @Bean
    public Step xmlToJdbcChunkJobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        LOG.info("Calling XmlChunkStep method...");
        return new StepBuilder("chunkStep",jobRepository)
                .<StudentXml, StudentXml >chunk(3, transactionManager)
                .reader(chunkReader)
                .processor(chunkItemProcessor)
                .writer(chunkWriter)
                .build();
    }

}
