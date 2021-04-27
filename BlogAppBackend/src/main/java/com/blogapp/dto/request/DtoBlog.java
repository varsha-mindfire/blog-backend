 package com.blogapp.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class DtoBlog {
	private String id;
	private String title;
	private String category;
	private String description;
	private String createDate;
	private String url;
	private MultipartFile file;
	public DtoBlog() {}
	public DtoBlog(String title, String category, String description, String createDate,MultipartFile file) {
		super();
		this.title = title;
		this.category = category;
		this.description = description;
		this.createDate = createDate;
		this.file=file;
		
//		this.setUrl(url);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
}