package com.hardik.bhagvadgita.schedule.jobs;

import java.io.IOException;
import java.util.HashMap;

import javax.mail.MessagingException;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.hardik.bhagvadgita.entity.User;
import com.hardik.bhagvadgita.exceptions.InvalidEmailException;
import com.hardik.bhagvadgita.schedule.scheduler.MailServiceScheduler;
import com.hardik.bhagvadgita.service.EmailService;
import com.hardik.bhagvadgita.utility.ModelMap;

import freemarker.template.TemplateException;

@Component
public class MailServiceJob implements Job{
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ModelMap modelMap;
	
	@Autowired
	private MailServiceScheduler mailServiceScheduler;
	
	public MailServiceJob() {
		super();
	}
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		JobDataMap dataMap = context.getTrigger().getJobDataMap();
		
		String fullName = dataMap.getString("fullName");
		String email = dataMap.getString("email");
		String verse = dataMap.getString("verse");
		JobDataMap map = context.getMergedJobDataMap();
		User user = (User)map.get("user");
		
		if (user.getVerse()==801) {
			try {
				emailService.sendEmail(email, new HashMap<String,Object>());
				mailServiceScheduler.removeTrigger(email);
			} catch (SchedulerException | MessagingException | IOException | TemplateException e) {
				e.printStackTrace();
			}
		}
		try {
			emailService.sendEmail(email, modelMap.generateMap(fullName, email, user.incrementVerse()));
		} catch (MessagingException | IOException | TemplateException | ObjectAlreadyExistsException e) {
			throw new InvalidEmailException();
		}	
	}

}
