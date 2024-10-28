package com.learnwithak.spring.batch.job.reader.json;

import com.learnwithak.spring.batch.job.config.JsonChunkJobConfiguration;
import com.learnwithak.spring.batch.job.model.json.StudentJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class JsonFileItemReader {
    public static final Logger LOG = LoggerFactory.getLogger(JsonFileItemReader.class);
    @Bean
    public JsonItemReader<StudentJson> jsonFileReader(){
        LOG.info("Calling JsonFileItemReader class of method :: jsonFileReader");
        JsonItemReader<StudentJson> jsonFileReader = new JsonItemReader<StudentJson>();
        jsonFileReader.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-chunk-datasource\\src\\main\\resources\\input\\json\\students.json")));

        jsonFileReader.setJsonObjectReader(new JacksonJsonObjectReader<>(StudentJson.class));
        //To read only 8 item
        jsonFileReader.setMaxItemCount(8);
        // to skip 2 value from json file
        jsonFileReader.setCurrentItemCount(2);
        return jsonFileReader;

    }

}
