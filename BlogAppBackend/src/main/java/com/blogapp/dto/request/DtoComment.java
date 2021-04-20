package com.blogapp.dto.request;

import java.time.Instant;

public class DtoComment {
	private String id;
	private String blogId;
	private String comment;
	private Instant createdDate;
	private String username;
	
	
	
	public DtoComment(String blogId, String comment, Instant createdDate, String username) {
		this.blogId = blogId;
		this.comment = comment;
		this.createdDate = createdDate;
		this.username = username;
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

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}