package com.hardik.bhagvadgita.utility;

import org.springframework.stereotype.Component;

@Component
public class ChapterVerseHandler {
	
	public String getChapterAndVerse(int verse) {
		if (verse<=47) {
			return 1+"."+verse;
		}
		else if(verse>=48 && verse<=119) {
			return 2+"."+(verse-47);
		}
		else if(verse>=120&&verse<=162) {
			return 3+"."+(verse-119);
		}
		else if(verse>=163&&verse<=204) {
			return 4+"."+(verse-162);
		}
		else if(verse>=205&&verse<=233) {
			return 5+"."+(verse-204);
		}
		else if(verse>=234&&verse<=280) {
			return 6+"."+(verse-233);
		}
		else if(verse>=281&&verse<=310) {
			return 7+"."+(verse-280);
		}
		else if(verse>=311&&verse<=338) {
			return 8+"."+(verse-310);
		}
		else if(verse>=339&&verse<=372) {
			return 9+"."+(verse-338);
		}
		else if(verse>=373&&verse<=414) {
			return 10+"."+(verse-372);
		}
		else if(verse>=415&&verse<=469) {
			return 11+"."+(verse-414);
		}
		else if(verse>=470&&verse<=489) {
			return 12+"."+(verse-469);
		}
		else if(verse>=490&&verse<=524) {
			return 13+"."+(verse-489);
		}
		else if(verse>=525&&verse<=551) {
			return 14+"."+(verse-524);
		}
		else if(verse>=552&&verse<=571) {
			return 15+"."+(verse-551);
		}
		else if(verse>=572&&verse<=595) {
			return 16+"."+(verse-571);
		}
		else if(verse>=596&&verse<=623) {
			return 17+"."+(verse-595);
		}
		else 
			return 18+"."+(verse-623);
	}
}
