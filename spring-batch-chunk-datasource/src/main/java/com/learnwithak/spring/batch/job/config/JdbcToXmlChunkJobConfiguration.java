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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class JdbcToXmlChunkJobConfiguration {
    public static final Logger LOG = LoggerFactory.getLogger(JdbcToXmlChunkJobConfiguration.class);
    @Autowired
    JdbcCursorItemReader<StudentXml> chunkReader;
    @Autowired
    StaxEventItemWriter<StudentXml> chunkWriter;
    @Autowired
    XmlItemProcessor chunkItemProcessor;

    public JdbcToXmlChunkJobConfiguration(JdbcCursorItemReader<StudentXml> chunkReader,
                                          StaxEventItemWriter<StudentXml> chunkWriter,
                                          XmlItemProcessor chunkItemProcessor) {
        this.chunkReader = chunkReader;
        this.chunkWriter = chunkWriter;
        this.chunkItemProcessor = chunkItemProcessor;
    }

    @Bean
    public Job jdbcToXmlChunkJob(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager){
        LOG.info("Calling XMLChunkJob method...");
        return new JobBuilder("JdbcToXmlChunkJob", jobRepository)
                .start(jdbcToXmlChunkJobStep(jobRepository,transactionManager))
                .build();
    }
    @Bean
    public Step jdbcToXmlChunkJobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        LOG.info("Calling XmlChunkStep method...");
        return new StepBuilder("chunkStep",jobRepository)
                .<StudentXml, StudentXml >chunk(3, transactionManager)
                .reader(chunkReader)
                .processor(chunkItemProcessor)
                .writer(chunkWriter)
                .build();
    }

}
