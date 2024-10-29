package com.learnwithak.spring.batch.job.processor.json;

import com.learnwithak.spring.batch.job.model.json.StudentJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class JsonItemProcessor implements ItemProcessor<StudentJson, StudentJson>{
    public static final Logger LOG = LoggerFactory.getLogger(JsonItemProcessor.class);

    @Override
    public StudentJson process(StudentJson item) throws Exception {
        LOG.info("Inside Item processor :: Converting Student Item to Upper Case");
        if (item != null){
            item.setFirstName(item.getFirstName().toUpperCase());
            item.setLastName(item.getLastName().toUpperCase());
            item.setEmail(item.getEmail().toUpperCase());
        }
        return item;
    }
}
