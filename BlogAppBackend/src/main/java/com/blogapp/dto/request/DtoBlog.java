 package com.blogapp.dto.request;

import lombok.Builder;
@Builder
public class DtoBlog {
	private String id;
	private String title;
	private String category;
	private String description;
	private String createDate;
	private String url;
	public DtoBlog() {}
	public DtoBlog(String title, String section, String description, String createDate, String url) {
		super();
		this.title = title;
		this.category = section;
		this.description = description;
		this.createDate = createDate;
		this.setUrl(url);
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
	
	public void setCategory(String section) {
		this.category = section;
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
}