package com.hardik.bhagvadgita.schedule.jobs;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.stereotype.Component;

@Component
public class MailServiceJobDetails {
	
	private static JobDetail jobDetails = JobBuilder.newJob(MailServiceJob.class).withIdentity("mailService").storeDurably(true).build();

	public JobDetail getJobDetail() {
		return jobDetails;
	}
}
