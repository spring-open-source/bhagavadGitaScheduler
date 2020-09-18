package com.hardik.bhagvadgita.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Verses {
	
	private String accessToken;
	
	private String url = "https://bhagavadgita.io/api/v1/chapters/1/verses?access_token=";
	
	private RestTemplate restTemplate;

	public Verses() {
		super();
	}

	@Autowired
	public Verses(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public Object getVerses(String chapterNumber) {
		return restTemplate.getForObject((url+getAccessToken()).replace("/1/", "/"+chapterNumber+"/"), Object.class);
	}

}
