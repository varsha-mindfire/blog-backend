package com.blogapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="comments")
public class Comment {
	private String id;
	private String comment;
	private String blogid;
	private String username;
//	private User user;
//	private Blog blog;
	public Comment() {}
	public Comment(String id, String comment, String blogid,String username) {
		this.id = id;
		this.comment = comment;
		this.blogid = blogid;
		this.username=username;
//		this.user = user;
		
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
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	

}
