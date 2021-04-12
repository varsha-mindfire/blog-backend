package com.blogapp.dto.response;

public class BlogResponse {
	private String id;
    private String title;
    private String section;
    private String description;
    private String userName;
    public BlogResponse() {}
	
//	public BlogResponse(String id, String title, String section, String description, String userName) {
//		this.id = id;
//		this.title = title;
//		this.section = section;
//		this.description = description;
//		this.userName = userName;
//	}
	
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
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}



}
