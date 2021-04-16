package com.blogapp.dto.request;

public class DtoLike {
	private String id;
	private Integer like;
	private String blogId;
	
	public DtoLike() {}
	
	public DtoLike(String id, Integer like, String blogId) {
		this.id = id;
		this.like = like;
		this.blogId = blogId;
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
	
}
	
	