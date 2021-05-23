package com.blogapp.dto.response;

import com.blogapp.constants.Emessage;

/**
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */

public class EmessageResponse {
	private Emessage message;

	public EmessageResponse(Emessage message) {
		this.message = message;
	}

	public Emessage getMessage() {
		return message;
	}

	public void setMessage(Emessage message) {
		this.message = message;
	}

}
