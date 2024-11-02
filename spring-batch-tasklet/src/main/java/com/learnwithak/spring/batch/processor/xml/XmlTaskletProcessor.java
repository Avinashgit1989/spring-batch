package com.learnwithak.spring.batch.processor.xml;

import com.learnwithak.spring.batch.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class XmlTaskletProcessor implements ItemProcessor<Student, Student> {
    public static final Logger LOG = LoggerFactory.getLogger(XmlTaskletProcessor.class);

    @Override
    public Student process(Student item) throws Exception {
        LOG.info("Inside Item processor :: Converting Student Item to Upper Case");
        if (item != null){
            item.setFirstName(item.getFirstName().toUpperCase());
            item.setLastName(item.getLastName().toUpperCase());
            /**I have added if block to apply the retry functionality
             *  that i have added in CsvChunkJobConfigurationWithFaultTolerance config class.
             *  you can comment if you have no retry functionality is required.
             */
            if ("retry@example.com".equals(item.getEmail())) {
                throw new RuntimeException("Transient error, retry this student.");
            }else {
                item.setEmail(item.getEmail().toUpperCase());
            }
        }
        return item;
    }
}
