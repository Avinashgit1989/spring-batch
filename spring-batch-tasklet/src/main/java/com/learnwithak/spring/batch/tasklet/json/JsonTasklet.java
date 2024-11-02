package com.learnwithak.spring.batch.tasklet.json;

import com.learnwithak.spring.batch.model.Student;
import com.learnwithak.spring.batch.processor.csv.CsvTaskletProcessor;
import com.learnwithak.spring.batch.processor.json.JsonTaskletProcessor;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class JsonTasklet implements Tasklet {

    public static final Logger LOG = LoggerFactory.getLogger(JsonTasklet.class);
    @Autowired
    private final JsonItemReader<Student> jsonItemReader;
    @Autowired
    private final JsonFileItemWriter<Student> jsonFileItemWriter;
    @Autowired
    private final JsonTaskletProcessor jsonTaskletProcessor;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Student> students = new ArrayList<>();

        // Open readers and writers
        jsonItemReader.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());
        jsonFileItemWriter.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());

        // Read each student from the input CSV
            Student student;
            while ((student = jsonItemReader.read()) != null) {
                Student processedStudent = jsonTaskletProcessor.process(student);
                students.add(processedStudent);
            }

        // Write students to the output CSV
        jsonFileItemWriter.write(new Chunk<>(students));
            //closing reader and writer
        jsonItemReader.close();
        jsonFileItemWriter.close();

        return RepeatStatus.FINISHED;
    }
}
