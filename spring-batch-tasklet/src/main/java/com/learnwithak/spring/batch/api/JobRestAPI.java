package com.learnwithak.spring.batch.api;

import com.learnwithak.spring.batch.tasklet.FirstTaskLet;
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
public class JobRestAPI {
    public static final Logger LOG = LoggerFactory.getLogger(JobRestAPI.class);
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job firstJob;

    @Autowired
    private Job csvTaskletJob;

    @Autowired
    private Job jsonTaskletJob;


    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("CurrentTime", new JobParameter<>(LocalDateTime.now(), LocalDateTime.class));
        JobParameters jobParameters = new JobParameters(params);
        if (jobName.equalsIgnoreCase("FirstJob")) {
            LOG.info("Tasklet Job Executing...");
            jobLauncher.run(firstJob, jobParameters);
        }
        return jobName+ " Started..";
    }

    @GetMapping("/start/tasklet/csv/{jobName}")
    public String startTaskletCsvJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("CurrentTime", new JobParameter<>(LocalDateTime.now(), LocalDateTime.class));
        JobParameters jobParameters = new JobParameters(params);
        if (jobName.equalsIgnoreCase("CsvTaskletJob")) {
            LOG.info("Tasklet CSV Job Executing...");
            jobLauncher.run(csvTaskletJob, jobParameters);
        }
        return jobName+ " Started..";
    }

    @GetMapping("/start/tasklet/json/{jobName}")
    public String startTaskletJsonJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("CurrentTime", new JobParameter<>(LocalDateTime.now(), LocalDateTime.class));
        JobParameters jobParameters = new JobParameters(params);
        if (jobName.equalsIgnoreCase("JsonTaskletJob")) {
            LOG.info("Tasklet Json Job Executing...");
            jobLauncher.run(jsonTaskletJob, jobParameters);
        }
        return jobName+ " Started..";
    }
}
