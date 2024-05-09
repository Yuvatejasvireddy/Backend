package com.teju.response;


public class AuthResponse {

	private String jwt;
	private String messgae;
	private String status;
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMessgae() {
		return messgae;
	}
	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}
	public String getStatus() {
		return status;
	}
	public AuthResponse() {
		super();
	}
	public AuthResponse(String jwt, String messgae, String status) {
		super();
		this.jwt = jwt;
		this.messgae = messgae;
		this.status = status;
	}
	public void setStatus(String b) {
		this.status = b;
	}
	
}
