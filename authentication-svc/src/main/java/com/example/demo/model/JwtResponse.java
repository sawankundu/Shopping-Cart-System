package com.example.demo.model;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class JwtResponse {

	String token;

	private UserDetails userDetails;

	public JwtResponse() {
		// TODO Auto-generated constructor stub
	}

	public JwtResponse(String token, UserDetails userDetails) {
		super();
		this.token = token;
		this.userDetails = userDetails;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
