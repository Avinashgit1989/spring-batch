package com.learnwithak.spring.batch.job.processor.csv;

import com.learnwithak.spring.batch.job.model.csv.StudentCsv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CsvItemProcessor implements ItemProcessor<StudentCsv, StudentCsv>{
    public static final Logger LOG = LoggerFactory.getLogger(CsvItemProcessor.class);

    @Override
    public StudentCsv process(StudentCsv item) throws Exception {
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
