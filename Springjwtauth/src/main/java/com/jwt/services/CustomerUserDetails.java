package com.jwt.services;


import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwt.document.Blog;
import com.jwt.model.User;
import com.jwt.model.UserModel;
import com.jwt.repo.Blogrepo;
import com.jwt.repo.UserRepository;
@Service
public class CustomerUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;

	private String id;

	private String username;

	private String email;
	
	@Autowired
	private UserRepository UserRepository;
	@Autowired
	private Blogrepo blogrepo;
	

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	public CustomerUserDetails() {}
	public CustomerUserDetails(String id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static CustomerUserDetails build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new CustomerUserDetails(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}

	 @Transactional
	    public String createResource(UserModel userModel){
	        if (!UserRepository.existsByEmail(userModel.getEmail())){
	            User user = new User();
	            BeanUtils.copyProperties(userModel,user);
	            try {
	                UserRepository.save(user);
	                userModel.getBlog().stream().forEach(c -> {
	                    Blog blog = new Blog();
	                    c.setEmail(userModel.getEmail());
	                    BeanUtils.copyProperties(c, blog);
	                    try {
	                    	blogrepo.save(blog);
	                    }catch (Exception e){
	                        throw e;
	                    }

	                });
	            }catch (Exception e){
	                throw e;
	            }

	            return "Resource added successfully!";
	        }else {
	            return "Duplicate resource";
	        }
	    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CustomerUserDetails user = (CustomerUserDetails) o;
		return Objects.equals(id, user.id);
	}
}
