package com.jwt.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class UserModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String Id;
	
	private String email;
	
	private String username;
	
	private String password;
	private List<BlogModel> blog;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	public UserModel() {}
	public UserModel( String email, String password,String username,List<BlogModel> blog) {
		this.email = email;
		this.password = password;
		this.username=username;
		this.blog=blog;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		this.Id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public List<BlogModel> getBlog() {
		return blog;
	}
	public void setBlog(List<BlogModel> blog) {
		this.blog = blog;
	}
	
	

}
