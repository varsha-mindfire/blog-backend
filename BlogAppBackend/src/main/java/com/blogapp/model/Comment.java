package com.blogapp.model;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;
//Comment model for storing user comments
@Document(collection="comments")
public class Comment {
	private String id;
	private String comment;
	private String blogid;
	private String username;
	private Instant createDate;
	
	public Comment() {}
	
	public Comment(String id, String comment, String blogid,String username,Instant createDate) {
		this.id = id;
		this.comment = comment;
		this.blogid = blogid;
		this.username=username;		
	}
	
	public String getBlogid() {
		return blogid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}
	
	public Comment(String comment) {
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

	public Instant getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Instant createDate) {
		this.createDate = createDate;
	}
	
}
