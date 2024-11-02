package com.learnwithak.spring.batch.tasklet.csv;

import com.learnwithak.spring.batch.model.Student;
import com.learnwithak.spring.batch.processor.csv.CsvTaskletProcessor;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CsvTasklet implements Tasklet {

    public static final Logger LOG = LoggerFactory.getLogger(CsvTasklet.class);
    @Autowired
    private final FlatFileItemReader<Student> flatFileItemReader;
    @Autowired
    private final FlatFileItemWriter<Student> flatFileItemWriter;
    @Autowired
    private final CsvTaskletProcessor csvTaskletProcessor;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Student> students = new ArrayList<>();

        // Open readers and writers
        flatFileItemReader.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());
        flatFileItemWriter.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());

        // Read each student from the input CSV
            Student student;
            while ((student = flatFileItemReader.read()) != null) {
                Student processedStudent = csvTaskletProcessor.process(student);
                students.add(processedStudent);
            }

        // Write students to the output CSV
        flatFileItemWriter.write(new Chunk<>(students));
            //closing reader and writer
        flatFileItemReader.close();
        flatFileItemWriter.close();

        return RepeatStatus.FINISHED;
    }
}
