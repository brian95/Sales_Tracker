package com.rhcloud.rdisalestracker.user;

public class User {
	private long id;
	private String username;
	private String password;
	
	public User(long id, String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
