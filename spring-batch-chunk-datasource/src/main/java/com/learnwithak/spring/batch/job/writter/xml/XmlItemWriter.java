package com.learnwithak.spring.batch.job.writter.xml;

import com.learnwithak.spring.batch.job.model.xml.StudentXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;

@Configuration
public class XmlItemWriter {
    public static final Logger LOG = LoggerFactory.getLogger(XmlItemWriter.class);

    @Bean
    public StaxEventItemWriter<StudentXml> xmlFileItemWriter(){
        LOG.info("Calling XmlItemWriter of method :: xmlFileItemWriter");
        StaxEventItemWriter<StudentXml> staxEventItemWriter = new StaxEventItemWriter<StudentXml>();
        staxEventItemWriter.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-chunk-datasource\\src\\main\\resources\\output\\xml\\students.xml")));
        staxEventItemWriter.setRootTagName("students");
        staxEventItemWriter.setMarshaller(new Jaxb2Marshaller(){
            {setClassesToBeBound(StudentXml.class);}
        });
        return staxEventItemWriter;
    }
}
