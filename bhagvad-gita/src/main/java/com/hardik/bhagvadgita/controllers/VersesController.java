package com.hardik.bhagvadgita.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hardik.bhagvadgita.service.Verses;

@Controller
public class VersesController {
	
	private Verses verses;
	
	public VersesController() {
		super();
	}
	
	@Autowired
	public VersesController(Verses verses) {
		super();
		this.verses = verses;
	}

	@GetMapping("/verses")
	public String showVerses(@RequestParam("chapterNumber")String chapterNumber, Model model) {
		model.addAttribute("verses", verses.getVerses(chapterNumber));
		return "verses";
	}

}
