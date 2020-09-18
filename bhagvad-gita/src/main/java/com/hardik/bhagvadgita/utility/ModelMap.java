package com.hardik.bhagvadgita.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hardik.bhagvadgita.service.Verse;

@Component
public class ModelMap {
	
	private Verse verse;
	
	@Autowired
	public ModelMap(Verse verse) {
		super();
		this.verse = verse;
	}

	public Map<String, Object> generateMap(String fullName, String email, Integer verse){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", fullName);
		map.put("email", email);
		map.put("body", this.verse.getVerse(verse));
		return map;
	}

}
