package com.learnwithak.spring.batch.job.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/job")
public class JobRestApi {
    public static final Logger LOG = LoggerFactory.getLogger(JobRestApi.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job csvChunkjob;
    @Autowired
    private Job jsonChunkjob;

    @Autowired
    private Job xmlChunkjob;
    @Autowired
    private Job csvToJdbcChunkjob;

    @Autowired
    private Job jdbcToCsvChunkJob;
    @GetMapping("/start/csv/{jobName}")
    public String startCsvJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("CurrentTime", new JobParameter<>(LocalDateTime.now(), LocalDateTime.class));
        JobParameters jobParameters = new JobParameters(params);
        if (jobName.equalsIgnoreCase("csvChunkJob")) {
            LOG.info("CSV Logger API is calling...");
            jobLauncher.run(csvChunkjob, jobParameters);
        }
        LOG.info("Job Started Time :: "+LocalDateTime.now());
        return jobName+ " Started successfully ...";
    }
    @GetMapping("/start/json/{jobName}")
    public String startJsonJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("CurrentTime", new JobParameter<>(LocalDateTime.now(), LocalDateTime.class));
        JobParameters jobParameters = new JobParameters(params);
        if (jobName.equalsIgnoreCase("jsonChunkJob")) {
            LOG.info("JSON API is calling...");
            jobLauncher.run(jsonChunkjob, jobParameters);
        }
        LOG.info("Job Started Time :: "+LocalDateTime.now());
        return jobName+ " Started successfully ...";
    }

    @GetMapping("/start/xml/{jobName}")
    public String startXMLJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("CurrentTime", new JobParameter<>(LocalDateTime.now(), LocalDateTime.class));
        JobParameters jobParameters = new JobParameters(params);
        if (jobName.equalsIgnoreCase("xmlChunkJob")) {
            LOG.info("XML API is calling...");
            jobLauncher.run(xmlChunkjob, jobParameters);
        }
        LOG.info("Job Started Time :: "+LocalDateTime.now());
        return jobName+ " Started successfully ...";
    }

    @GetMapping("/start/csv_to_jdbc/{jobName}")
    public String startCsvToJdbcJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("CurrentTime", new JobParameter<>(LocalDateTime.now(), LocalDateTime.class));
        JobParameters jobParameters = new JobParameters(params);
        if (jobName.equalsIgnoreCase("csvToJdbcChunkJob")) {
            LOG.info("Csv To Jdbc API is calling...");
            jobLauncher.run(csvToJdbcChunkjob, jobParameters);
        }
        LOG.info("Job Started Time :: "+LocalDateTime.now());
        return jobName+ " Started successfully ...";
    }

    @GetMapping("/start/jdbc_to_csv/{jobName}")
    public String startJdbcToCsvJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("CurrentTime", new JobParameter<>(LocalDateTime.now(), LocalDateTime.class));
        JobParameters jobParameters = new JobParameters(params);
        if (jobName.equalsIgnoreCase("jdbcToCsvChunkJob")) {
            LOG.info("Jdbc To csv API is calling...");
            jobLauncher.run(jdbcToCsvChunkJob, jobParameters);
        }
        LOG.info("Job Started Time :: "+LocalDateTime.now());
        return jobName+ " Started successfully ...";
    }
}
