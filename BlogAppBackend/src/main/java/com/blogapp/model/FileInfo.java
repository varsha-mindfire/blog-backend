package com.blogapp.model;

/**
 * File model for storing file Info
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */
public class FileInfo {
	private String name;
	private String url;

	public FileInfo(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}