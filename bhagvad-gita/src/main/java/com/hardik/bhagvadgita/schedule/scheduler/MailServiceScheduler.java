package com.hardik.bhagvadgita.schedule.scheduler;

import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hardik.bhagvadgita.AutowiringSpringBeanJobFactory;
import com.hardik.bhagvadgita.exceptions.EmailAlreadyRegisteredException;
import com.hardik.bhagvadgita.schedule.jobs.MailServiceJobDetails;

@Component
public class MailServiceScheduler {
	
	private Scheduler scheduler;
	
	private MailServiceJobDetails mailServiceJobDetails;
	
	private AutowiringSpringBeanJobFactory jobFactory;
	
	private ApplicationContext applicationContext;
	
	@Autowired
	public MailServiceScheduler(MailServiceJobDetails mailServiceJobDetails,AutowiringSpringBeanJobFactory jobFactory,ApplicationContext applicationContext) throws SchedulerException {
		super();
		this.scheduler = StdSchedulerFactory.getDefaultScheduler();
		this.mailServiceJobDetails = mailServiceJobDetails;
		this.jobFactory = jobFactory;
		this.applicationContext = applicationContext;
	}
	
	public void start() throws SchedulerException {
		if (!this.scheduler.isStarted()) {
			this.scheduler.start();
			jobFactory.setApplicationContext(applicationContext);
			this.scheduler.setJobFactory(jobFactory);
			this.scheduler.addJob(mailServiceJobDetails.getJobDetail(), false);
		}
	}
	
	public void addTrigger(Trigger trigger) throws SchedulerException {
		try {
			this.scheduler.scheduleJob(trigger);
		}catch(ObjectAlreadyExistsException exception) {
			throw new EmailAlreadyRegisteredException();
		}
	}
	
	public void removeTrigger(String email) throws SchedulerException {
		this.scheduler.unscheduleJob(new TriggerKey(email));
	}
}
