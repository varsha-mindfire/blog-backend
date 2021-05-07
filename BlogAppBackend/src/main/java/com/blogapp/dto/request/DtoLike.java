package com.blogapp.dto.request;

//Data transfer object for liking blogs
public class DtoLike {
	private String id;
	private Integer like;
	private String blogId;
	private String username;
	
	public DtoLike() {}
	
	public DtoLike(String id, Integer like, String blogId,String username) {
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
	
	