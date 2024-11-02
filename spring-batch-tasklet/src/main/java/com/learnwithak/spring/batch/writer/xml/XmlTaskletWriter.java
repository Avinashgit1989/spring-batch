package com.learnwithak.spring.batch.writer.xml;

import com.learnwithak.spring.batch.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;

@Configuration
public class XmlTaskletWriter {
    public static final Logger LOG = LoggerFactory.getLogger(XmlTaskletWriter.class);

    @Bean
    public StaxEventItemWriter<Student> xmlFileItemWriter(){
        LOG.info("Calling XmlItemWriter of method :: xmlFileItemWriter");
        StaxEventItemWriter<Student> staxEventItemWriter = new StaxEventItemWriter<Student>();
        staxEventItemWriter.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-tasklet\\src\\main\\resources\\output\\xml\\students.xml")));
        staxEventItemWriter.setRootTagName("students");
        staxEventItemWriter.setMarshaller(new Jaxb2Marshaller(){
            {setClassesToBeBound(Student.class);}
        });
        return staxEventItemWriter;
    }
}
