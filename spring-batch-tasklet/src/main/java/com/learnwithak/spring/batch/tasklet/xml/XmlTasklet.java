package com.learnwithak.spring.batch.tasklet.xml;

import com.learnwithak.spring.batch.model.Student;
import com.learnwithak.spring.batch.processor.xml.XmlTaskletProcessor;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class XmlTasklet implements Tasklet {

    public static final Logger LOG = LoggerFactory.getLogger(XmlTasklet.class);
    @Autowired
    private final StaxEventItemReader<Student> staxEventItemReader;
    @Autowired
    private final StaxEventItemWriter<Student> staxEventItemWriter;
    @Autowired
    private final XmlTaskletProcessor xmlTaskletProcessor;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Student> students = new ArrayList<>();

        // Open readers and writers
        staxEventItemReader.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());
        staxEventItemWriter.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());

        // Read each student from the input CSV
            Student student;
            while ((student = staxEventItemReader.read()) != null) {
                Student processedStudent = xmlTaskletProcessor.process(student);
                students.add(processedStudent);
            }

        // Write students to the output CSV
        staxEventItemWriter.write(new Chunk<>(students));
            //closing reader and writer
        staxEventItemReader.close();
        staxEventItemWriter.close();

        return RepeatStatus.FINISHED;
    }
}
