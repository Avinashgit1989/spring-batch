package com.learnwithak.spring.batch.processor;

import org.springframework.batch.item.ItemProcessor;

public class FirstItemProcessor implements ItemProcessor<Integer, Long> {
    @Override
    public Long process(Integer item) throws Exception {
        System.out.println("Inside Item processor");
        return Long.valueOf(item+10);
    }
}
