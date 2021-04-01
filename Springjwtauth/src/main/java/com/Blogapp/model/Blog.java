package com.Blogapp.model;

import java.time.Instant;

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
@Document(collection="blogapp")
public class Blog {
private String id;
private String title;
private String section;
private String description;
private Instant createDate;
private String username;
public Blog() {}
public Blog(String title, String section, String description, Instant createDate, String username) {
	this.title = title;
	this.section = section;
	this.description = description;
	this.createDate = createDate;
	this.username = username;
}
public void setId(String id) {
	this.id=id;
	
}
public String getId(String id) {
	return id;
	
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getSection() {
	return section;
}
public void setSection(String section) {
	this.section = section;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Instant getCreateDate() {
	return createDate;
}
public void setCreateDate(Instant createDate) {
	this.createDate = createDate;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
}
