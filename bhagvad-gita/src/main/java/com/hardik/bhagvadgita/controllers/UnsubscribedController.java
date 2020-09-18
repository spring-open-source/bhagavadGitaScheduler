package com.hardik.bhagvadgita.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hardik.bhagvadgita.schedule.scheduler.MailServiceScheduler;
import com.hardik.bhagvadgita.service.EmailService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Controller
public class UnsubscribedController {
	
	private MailServiceScheduler mailServiceScheduler;
	
	private EmailService emailService;
	
	@Autowired
	public UnsubscribedController(MailServiceScheduler mailServiceScheduler,EmailService emailService) {
		super();
		this.mailServiceScheduler = mailServiceScheduler;
		this.emailService = emailService;
	}

	@GetMapping("/unsubscribed/{email}")
	public String unsubscribeHandler(@PathVariable("email")String email, Model model) throws SchedulerException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, MessagingException, IOException, TemplateException {
		mailServiceScheduler.removeTrigger(email);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unsubscribe", true);
		emailService.sendEmail(email, map);
		model.addAttribute("email", email);
		return "unsubscribed";
	}

}
