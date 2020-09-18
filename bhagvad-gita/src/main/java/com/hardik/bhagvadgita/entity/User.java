package com.hardik.bhagvadgita.entity;

public class User {
	
	private int verse;
	
	private String fullName;
	
	private String email;
	
	public User() {
		super();
	}

	public User(String fullName, String email, int verse) {
		super();
		this.verse = verse;
		this.fullName = fullName;
		this.email = email;
	}

	public int getVerse() {
		return verse;
	}

	public void setVerse(int verse) {
		this.verse = verse;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int incrementVerse() {
		setVerse(verse+1);
		return verse;
	}

}
