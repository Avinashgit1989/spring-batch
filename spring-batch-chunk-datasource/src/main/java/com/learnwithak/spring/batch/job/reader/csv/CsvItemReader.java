package com.learnwithak.spring.batch.job.reader.csv;

import com.learnwithak.spring.batch.job.model.csv.StudentCsv;
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

@Bean
    public FlatFileItemReader<StudentCsv> studentCsvFlatFileItemReader() throws UnexpectedInputException,
        ParseException, NonTransientResourceException {
        FlatFileItemReader<StudentCsv> flatFileItemReader = new FlatFileItemReader<StudentCsv>();
        flatFileItemReader.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-chunk-datasource\\src\\main\\resources\\input\\csv\\students.csv")));

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