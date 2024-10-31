package com.learnwithak.spring.batch.job.service;

import com.learnwithak.spring.batch.job.config.CsvChunkJobConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class JobServiceWithScheduler {
    public static final Logger LOG = LoggerFactory.getLogger(JobServiceWithScheduler.class);
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job csvChunkjob;

    /**This job will automatically start after 1 minute. it is a scheduled job that will run*/
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public  void scheduledJobService() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("CurrentTime", new JobParameter<>(LocalDateTime.now(), LocalDateTime.class));
        JobParameters jobParameters = new JobParameters(params);
        JobExecution jobExecution = null;
        LOG.info("Scheduled Job Started...");
        jobExecution = jobLauncher.run(csvChunkjob, jobParameters);
        LOG.info("Job Execution ID :: "+jobExecution.getJobId());
    }
}
