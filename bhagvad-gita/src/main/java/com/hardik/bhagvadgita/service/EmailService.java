package com.hardik.bhagvadgita.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.quartz.ObjectAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class EmailService {

	
	private JavaMailSender sender;
	
	private Configuration config;
	
	@Value("${spring.mail.username}")
	private String from;
	
	
	@Autowired
	public EmailService(JavaMailSender sender, Configuration config) {
		super();
		this.sender = sender;
		this.config = config;
	}

	public void sendEmail(String toMail,Map<String, Object> model) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException,ObjectAlreadyExistsException {
			MimeMessage message = sender.createMimeMessage();
		
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			helper.setSubject("Daily Bhagavad Gita Verse");

			
			Template t = config.getTemplate("mail-body.ftl");
			
			if (model.size()==0) {
				t = config.getTemplate("verse-complete.ftl");
				helper.setSubject("ALL VERSES COMPLETE!");
			}
			
			if (model.containsKey("unsubscribe")) {
				t = config.getTemplate("unsubscribed.ftl");
				helper.setSubject("Successfully Unsubscribed From Daily Bhagvad Gita Verses");		
			}
				
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(toMail);
			helper.setText(html, true);
			helper.setFrom(from);
			sender.send(message);
	}
}
