package com.hardik.bhagvadgita.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hardik.bhagvadgita.utility.ChapterVerseHandler;
import com.hardik.bhagvadgita.utility.SplitChapterVerse;

@Service
public class Verse {
	
	private ChapterVerseHandler chapterVerseHandler;
	
	private SplitChapterVerse splitChapterVerse;
	
	private RestTemplate restTemplate;
	
	private String accessToken;
	
	private String url = "https://bhagavadgita.io/api/v1/chapters/CHAPTER/verses/VERSE?access_token=";
	
	@Autowired
	public Verse(ChapterVerseHandler chapterVerseHandler, SplitChapterVerse splitChapterVerse,
			RestTemplate restTemplate) {
		super();
		this.chapterVerseHandler = chapterVerseHandler;
		this.splitChapterVerse = splitChapterVerse;
		this.restTemplate = restTemplate;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Object getVerse(int verse) {
		String chapterVerse = chapterVerseHandler.getChapterAndVerse(verse);
		List<Integer> list = splitChapterVerse.split(chapterVerse);
		
		int chapterNumber = list.get(0);
		int verseNumber = list.get(1);
				
		return restTemplate.getForObject(((url+getAccessToken()).replace("CHAPTER", chapterNumber+"")).replace("VERSE", (verseNumber+1)
				+""), Object.class);
		
	}	
}
