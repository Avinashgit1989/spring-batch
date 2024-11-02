package com.learnwithak.spring.batch.reader.json;

import com.learnwithak.spring.batch.model.Student;
import com.learnwithak.spring.batch.reader.csv.CsvTaskletReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
@Component
public class JsonTaskletReader {
    public static final Logger LOG = LoggerFactory.getLogger(JsonTaskletReader.class);
    @Bean
    public JsonItemReader<Student> jsonFileReader(){
        LOG.info("Calling JsonFileItemReader class of method :: jsonFileReader");
        JsonItemReader<Student> jsonFileReader = new JsonItemReader<Student>();
        jsonFileReader.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-tasklet\\src\\main\\resources\\input\\json\\students.json")));

        jsonFileReader.setJsonObjectReader(new JacksonJsonObjectReader<>(Student.class));
        //To read only 8 item
        // jsonFileReader.setMaxItemCount(8);
        // to skip 2 value from json file
        //jsonFileReader.setCurrentItemCount(2);
        return jsonFileReader;

    }
}
