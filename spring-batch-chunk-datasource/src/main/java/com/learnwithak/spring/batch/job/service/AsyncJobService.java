package com.learnwithak.spring.batch.job.service;

import com.learnwithak.spring.batch.job.api.JobRestApi;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@Service
@AllArgsConstructor
public class AsyncJobService {
    public static final Logger LOG = LoggerFactory.getLogger(AsyncJobService.class);
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job xmlChunkjob;

    @Async
    public  void asyncJobService(String jobName) throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException, JobParametersInvalidException,
            JobRestartException {

        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("CurrentTime", new JobParameter<>(LocalDateTime.now(), LocalDateTime.class));
        JobParameters jobParameters = new JobParameters(params);
        JobExecution jobExecution = null;
        if (jobName.equalsIgnoreCase("xmlChunkjob")) {
            LOG.info("Async Job executed....");
            jobExecution = jobLauncher.run(xmlChunkjob, jobParameters);
            LOG.info("Job Execution ID :: "+jobExecution.getJobId());
        }

    }
}
