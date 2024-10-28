package com.learnwithak.spring.batch.job.processor.csv;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CsvItemProcessor implements ItemProcessor<Integer, Long> {
    @Override
    public Long process(Integer item) throws Exception {
        System.out.println("Inside Item processor :: Converting integer to long and adding 10 to each item");
        return Long.valueOf(item+10);
    }
}
