package com.learnwithak.spring.batch.job.reader.xml;

import com.learnwithak.spring.batch.job.model.json.StudentJson;
import com.learnwithak.spring.batch.job.model.xml.StudentXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;

@Configuration
public class XmlFileItemReader {
    public static final Logger LOG = LoggerFactory.getLogger(XmlFileItemReader.class);
    @Bean
    public StaxEventItemReader<StudentXml> xmlFileReader(){
        LOG.info("Calling JsonFileItemReader class of method :: jsonFileReader");
        StaxEventItemReader<StudentXml> xmlFileReader = new StaxEventItemReader<StudentXml>();
        xmlFileReader.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-chunk-datasource\\src\\main\\resources\\input\\xml\\students.xml")));

        xmlFileReader.setFragmentRootElementName("student");
        xmlFileReader.setUnmarshaller(new Jaxb2Marshaller(){
            {
                setClassesToBeBound(StudentXml.class);
            }
        });
        //To read only 8 item
        // xmlFileReader.setMaxItemCount(8);
        // to skip 2 value from json file
        // xmlFileReader.setCurrentItemCount(2);
        return xmlFileReader;


    }

}
