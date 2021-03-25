package com.jwt.model;

import java.util.Date;
import java.util.List;

import com.jwt.document.Comment;

public class BlogModel {
	private String id;
	private String section;
	private String title;
	private String description;
	private Date createdAt;
	private String email;
	private List<Comment> comments;
	
	public BlogModel() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    public List<Comment> getComments() {
        return comments;
    }
    public void getComments(List<Comment> comments) {
        this.comments=comments;
    }

}
