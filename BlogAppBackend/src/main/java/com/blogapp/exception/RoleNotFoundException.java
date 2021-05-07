package com.blogapp.exception;

//Response message for role not found exception
public class RoleNotFoundException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	private String message;

	public RoleNotFoundException(String message) {
	    this.message = message;
	  }
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}