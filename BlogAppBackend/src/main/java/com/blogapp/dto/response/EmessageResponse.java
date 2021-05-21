package com.blogapp.dto.response;

import com.blogapp.constants.Emessage;

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
