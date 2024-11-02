package com.learnwithak.spring.batch.reader.xml;

import com.learnwithak.spring.batch.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class XmlTaskletReader {
    public static final Logger LOG = LoggerFactory.getLogger(XmlTaskletReader.class);
    @Bean
    public StaxEventItemReader<Student> xmlFileReader(){
        LOG.info("Calling XmlFileItemReader class of method :: XmlFileReader");
        StaxEventItemReader<Student> xmlFileReader = new StaxEventItemReader<Student>();
        xmlFileReader.setResource(new FileSystemResource(new File("E:\\spring-batch-git\\spring-batch\\spring-batch-tasklet\\src\\main\\resources\\input\\xml\\students.xml")));

        xmlFileReader.setFragmentRootElementName("student");
        xmlFileReader.setUnmarshaller(new Jaxb2Marshaller(){
            {
                setClassesToBeBound(Student.class);
            }
        });
        //To read only 8 item
        // xmlFileReader.setMaxItemCount(8);
        // to skip 2 value from json file
        // xmlFileReader.setCurrentItemCount(2);
        return xmlFileReader;


    }
}
