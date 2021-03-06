package com.blogapp.dto.response;

/**
 * Response message for uploading file
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */
public class FileUploadResponse {

	private String fileName;
	private String contentType;
	private String url;
	private byte[] data;
	private String path;

	public FileUploadResponse(String fileName, String contentType, String url, byte[] data, String path) {
		this.fileName = fileName;
		this.contentType = contentType;
		this.url = url;
		this.data = data;
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
