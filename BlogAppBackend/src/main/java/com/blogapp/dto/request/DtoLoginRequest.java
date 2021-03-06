package com.blogapp.dto.request;

/**
 * Data transfer object for login request
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */
public class DtoLoginRequest {

	String username;
	String password;

	public DtoLoginRequest() {
	}

	public DtoLoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", password=" + password + "]";
	}
}
