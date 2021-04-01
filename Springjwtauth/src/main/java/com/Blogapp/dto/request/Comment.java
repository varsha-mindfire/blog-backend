package com.Blogapp.dto.request;

public class Comment {
	private String id;
	private String comment;
	public Comment(String comment, String username, String blogid) {
		this.comment = comment;
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
	

}
