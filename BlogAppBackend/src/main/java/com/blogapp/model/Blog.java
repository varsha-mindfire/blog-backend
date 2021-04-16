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
private String section;
private String description;
private Integer likeCount=0;
//private User user;

private String username;
public Blog() {}

//public Blog(String title, String section, String description, Instant createDate, String username, User user,
//		Instant createdDate) {
//	super();
//	this.title = title;
//	this.section = section;
//	this.description = description;
//	this.createDate = createDate;
//	this.username = username;
//	this.user = user;
//	this.createdDate = createdDate;
//}

//public User getUser() {
//	return user;
//}
//
//public void setUser(User user) {
//	this.user = user;
//}

//public Instant getCreatedDate() {
//	return createdDate;
//}
//
//public void setCreatedDate(Instant createdDate) {
//	this.createdDate = createdDate;
//}

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
//public Instant getCreateDate() {
//	return createDate;
//}
//public void setCreateDate(Instant createDate) {
//	this.createDate = createDate;
//}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

//public User getUser() {
//	return user;
//}
//
//public void setUser(User user) {
//	this.user = user;
//}

}
