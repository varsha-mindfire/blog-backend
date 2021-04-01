package com.Blogapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="comments")
public class Commentmodel {
	private String id;
	private String comment;
	private String username;
	private String blogid;
	private Object details;
	public Commentmodel() {}
	public Commentmodel(String id, String comment, String username, String blogid, Object details) {
		this.id = id;
		this.comment = comment;
		this.username = username;
		this.blogid = blogid;
		this.details = details;
	}
	public Object getDetails() {
		return details;
	}
	public void setDetails(Object details) {
		this.details = details;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBlogid() {
		return blogid;
	}
	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}
	public Commentmodel(String comment) {
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
	

}
