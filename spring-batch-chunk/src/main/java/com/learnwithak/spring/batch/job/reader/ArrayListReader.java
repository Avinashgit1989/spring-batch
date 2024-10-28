package com.learnwithak.spring.batch.job.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class ArrayListReader implements ItemReader<Integer> {
    List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    int count =0;

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Integer item = null;
        if(count < list.size()){
            item = list.get(count);
            System.out.println("inside reader :: Reading item from array list");
            count++;
            return item;
        }
        count = 0;
        return null;

    }
}