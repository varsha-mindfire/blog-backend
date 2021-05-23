package com.blogapp.dto.response;

/**
 * Message response
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */
public class MessageResponse {

	private String message;

	public MessageResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
