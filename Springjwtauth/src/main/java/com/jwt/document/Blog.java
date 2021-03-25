package com.jwt.document;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="blog1")
public class Blog {
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String section;
	private String title;
	private String description;
	private Date createdAt;
	private String email;
	private List<Comment> comments;
	
	public Blog() {
	}
	public String getId() {
		return id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
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
	 public void getComments(List<Comment> comments) {
	        this.comments=comments;
	    }
    public List<Comment> getComments() {
        return comments;
    }
}