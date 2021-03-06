package com.blogapp.dto.request;

/**
 * DTO for sign up request
 * @author Varsha
 * @since 15/03/2021
 */
import java.util.Set;

public class DtoSignupRequest {

	private String username;
	private String email;
	private Set<String> role;
	private String password;
	private Integer blogcount = 0;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return this.role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public Integer getBlogcount() {
		return blogcount;
	}

	public void setBlogcount(Integer blogcount) {
		this.blogcount = blogcount;
	}
}
