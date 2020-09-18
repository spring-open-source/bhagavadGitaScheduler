package com.hardik.bhagvadgita.schedule.triggers;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDataMap;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hardik.bhagvadgita.entity.User;
import com.hardik.bhagvadgita.schedule.jobs.MailServiceJobDetails;
import com.hardik.bhagvadgita.utility.RandomNameGenerator;

@Component
@Scope("prototype")
public class MailServiceTrigger {
	
	private final RandomNameGenerator randomNameGenerator;
	
	private final MailServiceJobDetails jobDetails;
	
	@Autowired
	public MailServiceTrigger(RandomNameGenerator randomNameGenerator,MailServiceJobDetails jobDetails) {
		super();
		this.randomNameGenerator = randomNameGenerator;
		this.jobDetails = jobDetails;
	}

	public Trigger generateTrigger(User user) {
		JobDataMap map = new JobDataMap();
		map.put("user", user);
		return TriggerBuilder.newTrigger()
			   .withIdentity(user.getEmail())
			   .forJob(jobDetails.getJobDetail())
			   .withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?"))
			   .usingJobData("email", user.getEmail())
			   .usingJobData("fullName", user.getFullName())
			   .usingJobData("verse",String.valueOf(user.getVerse()))
			   .usingJobData(map)
			   .build();
	}

}
