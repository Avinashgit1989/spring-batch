package com.learnwithak.spring.batch.job.config;

import com.learnwithak.spring.batch.job.listener.SkipItemListener;
import com.learnwithak.spring.batch.job.model.csv.StudentCsv;
import com.learnwithak.spring.batch.job.processor.csv.CsvItemProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CsvChunkJobConfigurationWithFaultTolerance {
    public static final Logger LOG = LoggerFactory.getLogger(CsvChunkJobConfigurationWithFaultTolerance.class);
    @Autowired
    FlatFileItemReader<StudentCsv> chunkReader;
    @Autowired
    FlatFileItemWriter<StudentCsv> chunkWriter;
    @Autowired
    CsvItemProcessor chunkItemProcessor;
    @Autowired
    SkipItemListener skipListener;

    public CsvChunkJobConfigurationWithFaultTolerance(FlatFileItemReader<StudentCsv> chunkReader,
                                                      FlatFileItemWriter<StudentCsv> chunkWriter,
                                                      CsvItemProcessor chunkItemProcessor) {
        this.chunkReader = chunkReader;
        this.chunkWriter = chunkWriter;
        this.chunkItemProcessor = chunkItemProcessor;
    }

    @Bean
    public Job csvChunkJobWithFaultTolerance(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager){
        LOG.info("CSV Job is being called...");
        return new JobBuilder("CsvChunkJobWithFaultTolerance", jobRepository)
                .start(csvChunkJobStepWithFaultTolerance(jobRepository,transactionManager))
                .build();
    }
    @Bean
    public Step csvChunkJobStepWithFaultTolerance(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        LOG.info("CSV Steps is being called...");
        return new StepBuilder("chunkStep",jobRepository)
                .<StudentCsv, StudentCsv>chunk(3, transactionManager)
                .reader(chunkReader)
                .processor(chunkItemProcessor)
                .writer(chunkWriter)
                .faultTolerant()
                // Skip faulty records. you can also use Throwable.class in skip
                .skip(FlatFileParseException.class)
                // Allow up to 4 skips. if you will pass 2 then you will get parsing error because in students-with-error.csv file containing 3 incorrect item, and you are trying to skip only two.
                //.skipLimit(4) //if you are using skipPolicy(new AlwaysSkipItemSkipPolicy()) then no need to skip the item with manual count.
                // Retry operations for a specific exception
                .retry(RuntimeException.class)
                // Retry up to 3 times for each item
                .retryLimit(3)
                .skipPolicy(new AlwaysSkipItemSkipPolicy()) //it is use to skip the faulty item always.
                .listener(skipListener)//Added listener to write the corrupt data to file in skip directory under output directory
                .build();
    }

}
