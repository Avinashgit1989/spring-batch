package com.learnwithak.spring.batch.writer.csv;

import com.learnwithak.spring.batch.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CsvTaskletWriter {
    public static final Logger LOG = LoggerFactory.getLogger(CsvTaskletWriter.class);

    @Bean
    public FlatFileItemWriter<Student> studentCsvFlatFileItemWriter() throws Exception {
        LOG.info("Calling CsvItemWriter method :: studentCsvFlatFileItemWriter");
        FlatFileItemWriter<Student> flatFileItemWriter = new FlatFileItemWriter<Student>();
        //flatFileItemWriter.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-tasklet\\src\\main\\resources\\output\\csv\\students.csv")));
        flatFileItemWriter.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-tasklet\\src\\main\\resources\\output\\tsv\\students.tsv")));
        //flatFileItemWriter.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-tasklet\\src\\main\\resources\\output\\txt\\students.txt")));
        //flatFileItemWriter.setAppendAllowed(true); // Append mode if needed
        flatFileItemWriter.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("ID,First Name,Last Name,Email");
            }
        });
        flatFileItemWriter.setLineAggregator(new DelimitedLineAggregator<Student>(){
            {
              //  setDelimiter("\t"); // enable this for .tsv file
                setDelimiter(","); //enable this for .csv and .txt file
                setFieldExtractor(new BeanWrapperFieldExtractor<Student>(){
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
       // flatFileItemWriter.setShouldDeleteIfExists(true); // Remove file if exists to avoid append errors
        return flatFileItemWriter;

    }
}
