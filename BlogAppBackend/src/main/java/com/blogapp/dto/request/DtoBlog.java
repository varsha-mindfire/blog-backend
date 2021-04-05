package com.blogapp.dto.request;

import java.time.Instant;

import lombok.Builder;
@Builder
public class DtoBlog {
	private String id;
	private String title;
	private String section;
	private String description;
	private Instant createDate;
	public DtoBlog() {}
	public DtoBlog(String title, String section, String description, Instant createDate) {
		super();
		this.title = title;
		this.section = section;
		this.description = description;
		this.createDate = createDate;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
}
	