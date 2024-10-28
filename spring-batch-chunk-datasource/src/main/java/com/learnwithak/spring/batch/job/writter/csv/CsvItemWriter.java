package com.learnwithak.spring.batch.job.writter.csv;

import com.learnwithak.spring.batch.job.model.csv.StudentCsv;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;

@Configuration
public class CsvItemWriter {


    @Bean
    public FlatFileItemWriter<StudentCsv> studentCsvFlatFileItemWriter() throws Exception {
            System.out.println("inside writer :: Writing CSV item");
        FlatFileItemWriter<StudentCsv> flatFileItemWriter = new FlatFileItemWriter<StudentCsv>();
        flatFileItemWriter.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-chunk-datasource\\src\\main\\resources\\output\\csv\\students.csv")));

        flatFileItemWriter.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("ID,First Name,Last Name,Email");
            }
        });
        flatFileItemWriter.setLineAggregator(new DelimitedLineAggregator<StudentCsv>(){
            {
                setFieldExtractor(new BeanWrapperFieldExtractor<StudentCsv>(){
                    {
                        setNames(new String[] {"id","firstName", "lastName", "email"});
                    }
                });
                flatFileItemWriter.setFooterCallback(new FlatFileFooterCallback() {
                    @Override
                    public void writeFooter(Writer writer) throws IOException {
                        writer.write("Created @ "+ LocalDateTime.now());
                    }
                });
            }
        });
        return flatFileItemWriter;

    }
}
