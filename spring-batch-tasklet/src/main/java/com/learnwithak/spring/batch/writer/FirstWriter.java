package com.learnwithak.spring.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

public class FirstWriter implements ItemWriter<Long> {

    @Override
    public void write(Chunk<? extends Long> chunk) throws Exception {
        System.out.println("inside writer");
        chunk.getItems().stream().forEach(System.out::println);
    }
}
