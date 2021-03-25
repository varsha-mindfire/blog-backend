package com.jwt.document;

import java.util.Date;

public class Comment {
	private String comment;
	private Date createdAt;
	private Comment() {}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
