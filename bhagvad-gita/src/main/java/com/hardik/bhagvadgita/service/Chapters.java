package com.hardik.bhagvadgita.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Chapters {
	
	private String accessToken;
	
	private String url = "https://bhagavadgita.io/api/v1/chapters?access_token=";
	
	private RestTemplate restTemplate;
	
	public Chapters() {
		super();
	}
	
	@Autowired
	public Chapters(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public Object getChapters() {
		return restTemplate.getForObject(url+getAccessToken(), Object.class);
	}
	
}
