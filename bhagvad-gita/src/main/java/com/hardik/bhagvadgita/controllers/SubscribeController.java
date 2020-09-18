package com.hardik.bhagvadgita.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.quartz.ObjectAlreadyExistsException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hardik.bhagvadgita.entity.User;
import com.hardik.bhagvadgita.exceptions.EmailAlreadyRegisteredException;
import com.hardik.bhagvadgita.exceptions.InvalidEmailException;
import com.hardik.bhagvadgita.schedule.scheduler.MailServiceScheduler;
import com.hardik.bhagvadgita.schedule.triggers.MailServiceTrigger;
import com.hardik.bhagvadgita.service.EmailService;
import com.hardik.bhagvadgita.utility.ModelMap;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Controller
public class SubscribeController {
	
	private MailServiceTrigger mailServiceTrigger;
	
	private ModelMap modelMap;
	
	private MailServiceScheduler mailServiceScheduler;
	
	private EmailService emailService;
	
	@Autowired
	public SubscribeController(MailServiceTrigger mailServiceTrigger, ModelMap modelMap,MailServiceScheduler mailServiceScheduler, EmailService emailService) {
		super();
		this.mailServiceTrigger = mailServiceTrigger;
		this.modelMap = modelMap;
		this.mailServiceScheduler = mailServiceScheduler;
		this.emailService = emailService;
	}

	@PostMapping("/subscribe")
	public String subscribeMailService(@RequestParam("email")String email, @RequestParam("name")String fullName,Model model) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, MessagingException, IOException, TemplateException, SchedulerException {
		model.addAttribute("email", email);
		Trigger trigger = mailServiceTrigger.generateTrigger(new User(fullName,email,0));
		mailServiceScheduler.start();
		try {		
			mailServiceScheduler.addTrigger(trigger);
		}catch(EmailAlreadyRegisteredException exception) {
			exception.printStackTrace();
			return "already-subscribed";
		}
		try {			
			emailService.sendEmail(email, modelMap.generateMap(fullName, email, 0));
		}
		catch(Exception exception) {
			exception.printStackTrace();
			return "invalid-mail";
		}
		return "subscribed";
	}
}
