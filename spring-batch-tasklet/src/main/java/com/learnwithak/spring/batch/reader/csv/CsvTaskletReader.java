package com.learnwithak.spring.batch.reader.csv;

import com.learnwithak.spring.batch.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class CsvTaskletReader{
    public static final Logger LOG = LoggerFactory.getLogger(CsvTaskletReader.class);
    @Bean
    public FlatFileItemReader<Student> csvFileItemReader() {
        LOG.info("Reader :: Reading Students details from CSV");
        FlatFileItemReader<Student> flatFileItemReader = new FlatFileItemReader<Student>();
        flatFileItemReader.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-tasklet\\src\\main\\resources\\input\\csv\\students.csv")));
        //flatFileItemReader.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-tasklet\\src\\main\\resources\\input\\csv\\students.txt")));
        flatFileItemReader.setLineMapper(new DefaultLineMapper<Student>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames("ID", "First Name", "Last Name", "Email");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Student>(){
                    {
                        setTargetType(Student.class);
                    }
                });
            }
        });
        //To skip the header name in database.
        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }

}
