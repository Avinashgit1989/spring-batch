package com.learnwithak.spring.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class FirstTaskLet implements Tasklet {
    public static final Logger LOG = LoggerFactory.getLogger(FirstTaskLet.class);
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOG.info("First taskLet executing");
        return RepeatStatus.FINISHED;
    }
}
