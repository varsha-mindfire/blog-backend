package com.blogapp.dto.response;

//Response message for download file.
public class FileDownloadResponse {
	private String data;
	
	public FileDownloadResponse() {}
	
	public FileDownloadResponse(String data) {
		this.data = data;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
