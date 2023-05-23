package com.panorbit.test.user;

public enum Gender {
	male("male"), female("female"), other("other");
	
	String gender;
	
	Gender(String gender) {
		this.gender = gender;
	}
}
