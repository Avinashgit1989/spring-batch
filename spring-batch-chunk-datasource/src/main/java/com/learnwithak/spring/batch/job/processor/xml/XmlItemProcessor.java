package com.learnwithak.spring.batch.job.processor.xml;

import com.learnwithak.spring.batch.job.model.xml.StudentXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class XmlItemProcessor implements ItemProcessor<StudentXml, StudentXml>{
    public static final Logger LOG = LoggerFactory.getLogger(XmlItemProcessor.class);

    @Override
    public StudentXml process(StudentXml item) throws Exception {
        LOG.info("Inside Item processor :: Converting Student Item to Upper Case");
        if (item != null){
            item.setFirstName(item.getFirstName().toUpperCase());
            item.setLastName(item.getLastName().toUpperCase());
            item.setEmail(item.getEmail().toUpperCase());
        }
        return item;
    }
}
