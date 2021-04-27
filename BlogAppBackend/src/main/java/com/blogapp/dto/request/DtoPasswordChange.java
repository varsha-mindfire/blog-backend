package com.blogapp.dto.request;

public class DtoPasswordChange {
	private String password;
	public DtoPasswordChange() {}

	public DtoPasswordChange(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
