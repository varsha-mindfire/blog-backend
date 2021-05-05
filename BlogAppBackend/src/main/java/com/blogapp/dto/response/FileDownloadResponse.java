package com.blogapp.dto.response;

public class FileDownloadResponse {
	private String data;
	public FileDownloadResponse() {}
	

	public FileDownloadResponse(String data) {
		super();
		this.data = data;
	}


	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	

}
