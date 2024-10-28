package com.learnwithak.spring.batch.job.writter.json;

import com.learnwithak.spring.batch.job.config.JsonChunkJobConfiguration;
import com.learnwithak.spring.batch.job.model.json.StudentJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class JsonItemWriter {
    public static final Logger LOG = LoggerFactory.getLogger(JsonItemWriter.class);

    @Bean
    public JsonFileItemWriter<StudentJson> jsonFileItemWriter(){
        LOG.info("Calling JsonItemWriter of method :: jsonFileItemWriter");
        FileSystemResource fileSystemResource =  new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-chunk-datasource\\src\\main\\resources\\output\\json\\students.json"));
        JsonFileItemWriter<StudentJson> jsonFileItemWriter = new JsonFileItemWriter<StudentJson>(
                fileSystemResource, new JacksonJsonObjectMarshaller<StudentJson>());
        return jsonFileItemWriter;
    }
}
