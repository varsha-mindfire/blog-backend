package com.blogapp.model;

import java.util.HashSet;
import java.util.Set;

//import javax.management.relation.Role;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * User model for storing user Info
 * 
 * @author Varsha
 *
 */

@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String Id;

	private String email;

	private String username;

	private String password;
	private Integer blogcount;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String email, String password, String username, Integer blogcount) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.blogcount = blogcount;
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

	public Integer getBlogcount() {
		return blogcount;
	}

	public void setBlogcount(Integer blogcount) {
		this.blogcount = blogcount;
	}

}