package com.blogapp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//Blog model for storing blog Info
@Document(collection="blogpost")
public class Blog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String title;
	private String category;
	private String description;
	private String createDate;
	private String url;
	private String path;
	private String name;
	private Integer likeCount=0;
	
	private String username;
	public Blog() {}
	
	
	public void setId(String id) {
		this.id=id;
		
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	
	public String getId() {
		return id;
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String section) {
		this.category = section;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

}
