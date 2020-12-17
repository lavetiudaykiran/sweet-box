package com.org.flightbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
	private String emailId;

	private String password;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
