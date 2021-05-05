package com.blogapp.dto.response;

public class FileDownloadResponse {
	private byte[] data;
	public FileDownloadResponse() {}
	

	public FileDownloadResponse(byte[] data) {
		super();
		this.data = data;
	}


	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	

}
