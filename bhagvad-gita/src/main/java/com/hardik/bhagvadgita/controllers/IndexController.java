package com.hardik.bhagvadgita.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hardik.bhagvadgita.service.AccessToken;
import com.hardik.bhagvadgita.service.Chapters;
import com.hardik.bhagvadgita.service.Verse;
import com.hardik.bhagvadgita.service.Verses;

@Controller
public class IndexController {
	
	private AccessToken accessToken;
	
	private Chapters chapters;
	
	private Verses verses;
	
	private Verse verse;
		
	public IndexController() {
		super();
	}

	@Autowired
	public IndexController(AccessToken accessToken, Chapters chapters, Verses verses,Verse verse) {
		super();
		this.accessToken = accessToken;
		this.chapters = chapters;
		this.verses = verses;
		this.verse = verse;
	}

	@GetMapping({"/","/home","/index"})
	public String showIndex() {
		String accessToken = this.accessToken.getToken();
		chapters.setAccessToken(accessToken);
		verses.setAccessToken(accessToken);
		verse.setAccessToken(accessToken);
		return "index";
	}

}
