package com.blogapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="likes")
public class Like {
	private String id;
	private Integer like;
	private String blogId;
	private String username;
	
	public Like() {}
	
	public Like(String id, Integer like, String blogId,String username) {
		this.id = id;
		this.like = like;
		this.blogId = blogId;
		this.username=username;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getLike() {
		return like;
	}
	
	public void setLike(Integer like) {
		this.like = like;
	}
	
	public String getBlogId() {
		return blogId;
	}
	
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

}
