package com.teju.request;

public class LoginRequest {

	public String username; 
	public String password;
	
	
	public LoginRequest() {
		super();
	}
	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
}
