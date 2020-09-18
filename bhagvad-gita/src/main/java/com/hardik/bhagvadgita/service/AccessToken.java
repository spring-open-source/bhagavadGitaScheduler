package com.hardik.bhagvadgita.service;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AccessToken {
	
	private RestTemplate restTemplate;

	public AccessToken() {
		super();
	}

	@Autowired
	public AccessToken(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}
	
	public String getToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("accept", "application/json");

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id","y3phuEmkEvzjz5gAW8nEqO3sxwxM1f4mCIL68jiw");
		map.add("client_secret","tNsqBqnIDWjI3D9s6mdpb0NljIPoFaPurDV4wIeK8QjyBqnl4s");
		map.add("grant_type", "client_credentials");
		map.add("scope", "verse chapter");

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

		ResponseEntity<Object> response =
		    restTemplate.exchange("https://bhagavadgita.io/auth/oauth/token",
		                          HttpMethod.POST,
		                          entity,
		                          Object.class);
		
		LinkedHashMap result = (LinkedHashMap)response.getBody();
		String accessToken = (String)result.get("access_token");

		return accessToken;
		
	}
	
}
