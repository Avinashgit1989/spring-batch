package com.learnwithak.spring.batch.writer.json;

import com.learnwithak.spring.batch.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class JsonTaskletWriter {
    public static final Logger LOG = LoggerFactory.getLogger(JsonTaskletWriter.class);

    @Bean
    public JsonFileItemWriter<Student> jsonFileItemWriter(){
        LOG.info("Calling JsonItemWriter of method :: jsonFileItemWriter");
        FileSystemResource fileSystemResource =  new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-tasklet\\src\\main\\resources\\output\\json\\students.json"));
        JsonFileItemWriter<Student> jsonFileItemWriter = new JsonFileItemWriter<Student>(
                fileSystemResource, new JacksonJsonObjectMarshaller<Student>());
        return jsonFileItemWriter;
    }
}
