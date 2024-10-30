package com.learnwithak.spring.batch.job.listener;

import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
/*This class will help to read the fault or corrupt data which will be skipped by
 using fault tolerance and write those data to skip directory in output directory.

 To check this functionality you have to uncomment the code available at line 30 in CsvItemReader.class
*/
@Component
public class SkipItemListener {
    @OnSkipInRead
    public void skipInRead(Throwable th){
        if(th instanceof FlatFileParseException){
            createFile("E:\\spring-batch-git\\spring-batch\\spring-batch-chunk-datasource\\src\\main\\resources\\output\\skip\\SkipInRead.txt",
        ((FlatFileParseException) th).getInput());
        }
    }

    public void createFile(String filePath, String data){
        try (FileWriter fileWriter = new FileWriter(new File(filePath), true)){
            fileWriter.write(data+ "\n");
        }catch (Exception e){

        }
    }
}
