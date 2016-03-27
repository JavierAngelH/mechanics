package com.mechanicshop.jobs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component("runSchedulerSMS")
public class RunSchedulerSMS {
 
  @Autowired
  private JobLauncher jobLauncher;
 
  @Autowired
  private Job sendSMSJob;
 
  public void run() {
 
    try {

    	Map<String, JobParameter> jobDataMap = new HashMap<String, JobParameter>();
    	jobDataMap.put("Date", new JobParameter(new Date())); 


    	
    	JobParameters parameters = new JobParameters(jobDataMap);

    	System.out.println("Start sending messages at: " + new Date());
 
	JobExecution execution = jobLauncher.run(sendSMSJob, parameters);
	System.out.println("Stop sending messages : " + execution.getStatus() + " at " + new Date());
    } catch (Exception e) {
	e.printStackTrace();
    }
 
  }
}