package com.learnwithak.spring.batch.job.writter;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ArrayItemWriter  implements ItemWriter<Long> {

    @Override
    public void write(Chunk<? extends Long> chunk) throws Exception {
        System.out.println("inside writer :: Writing array item");
        chunk.getItems().stream().forEach(System.out::println);
    }
}
