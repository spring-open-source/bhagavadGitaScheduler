package com.hardik.bhagvadgita.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hardik.bhagvadgita.service.Chapters;

@Controller
public class ChaptersController {
	
	private Chapters chapters;
	
	public ChaptersController() {
		super();
	}

	@Autowired
	public ChaptersController(Chapters chapters) {
		super();
		this.chapters = chapters;
	}
	
	@GetMapping("/chapters")
	public String showChapters(Model model) {
		model.addAttribute("chapters", chapters.getChapters());
		return "chapters";
	}

}
