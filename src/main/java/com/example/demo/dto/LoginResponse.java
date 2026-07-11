package com.example.demo.dto;

public class LoginResponse {
	
    private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LoginResponse(String message) {
		super();
		this.message = message;
	}

	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


}
