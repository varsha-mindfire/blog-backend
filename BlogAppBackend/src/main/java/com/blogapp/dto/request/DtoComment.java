package com.blogapp.dto.request;

public class DtoComment {
	private String id;
	private String blogId;
	private String comment;

	public DtoComment(String blogId, String comment) {
	this.blogId = blogId;
	this.comment = comment;
}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getBlogId() {
		return blogId;
	}
	
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	
}