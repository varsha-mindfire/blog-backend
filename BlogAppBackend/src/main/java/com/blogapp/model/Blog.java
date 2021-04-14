package com.blogapp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

private String username;
public Blog() {}

public void setId(String id) {
	this.id=id;
	
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

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getCreateDate() {
	return createDate;
}

public void setCreateDate(String createDate) {
	this.createDate = createDate;
}
}
