package com.hardik.bhagvadgita.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SplitChapterVerse {

	public List<Integer> split(String verse) {
		
		String stringArray[] = verse.split("[.]");
		
		List<Integer> temp = new ArrayList<Integer>();
		
		for (int i=0;i<2;i++) {
			temp.add(Integer.parseInt(stringArray[i]));
		}
		
		return temp;
		
	}
}
