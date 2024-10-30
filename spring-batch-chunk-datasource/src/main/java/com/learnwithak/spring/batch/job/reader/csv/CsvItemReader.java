package com.learnwithak.spring.batch.job.reader.csv;

import com.learnwithak.spring.batch.job.config.CsvChunkJobConfiguration;
import com.learnwithak.spring.batch.job.model.csv.StudentCsv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
@Configuration
public class CsvItemReader {
    public static final Logger LOG = LoggerFactory.getLogger(CsvItemReader.class);

@Bean
    public FlatFileItemReader<StudentCsv> studentCsvFlatFileItemReader() throws UnexpectedInputException,
        ParseException, NonTransientResourceException {
    LOG.info("Calling CsvItemReader method :: studentCsvFlatFileItemReader");
        FlatFileItemReader<StudentCsv> flatFileItemReader = new FlatFileItemReader<StudentCsv>();
        flatFileItemReader.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-chunk-datasource\\src\\main\\resources\\input\\csv\\students.csv")));
       //Note:: Use below code to check students-with-error.csv. In this case it will call the fault tolerance to retry.
       // flatFileItemReader.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-chunk-datasource\\src\\main\\resources\\input\\csv\\students_with_error.csv")));
        flatFileItemReader.setLineMapper(new DefaultLineMapper<StudentCsv>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames("ID", "First Name", "Last Name", "Email");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<StudentCsv>(){
                    {
                        setTargetType(StudentCsv.class);
                    }
                });
            }
        });
        //To skip the header name in database.
         flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }

}