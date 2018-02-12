package com.mukesh.ms.lab.employee;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class EmployeeApplication 
{
	
	@Autowired
	private static JobLauncher jobLauncher;
	
	@Autowired
	private static Job job;

	
	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		SpringApplication.run(EmployeeApplication.class, args);
		
		 JobParameters jobParameters = new JobParametersBuilder()
										.toJobParameters();
		 if(jobLauncher!=null)
		 {
			 jobLauncher.run(job, jobParameters);
		 }
		
	}
	
	
		
}
