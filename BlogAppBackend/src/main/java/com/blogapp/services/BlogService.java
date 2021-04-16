package com.blogapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogapp.dto.request.DtoBlog;
import com.blogapp.exception.BlogNotFoundException;
import com.blogapp.model.Blog;
import com.blogapp.repo.BlogRepository;

@Service
public class BlogService{
	@Autowired
	private BlogRepository blogRepository;
	
	public DtoBlog saveBlog(DtoBlog dtoBlog) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Blog b =new Blog();
		b.setTitle(dtoBlog.getTitle());
		b.setCategory(dtoBlog.getCategory());
		b.setDescription(dtoBlog.getDescription());
		b.setCreateDate(dtoBlog.getCreateDate());
		b.setUrl(dtoBlog.getUrl());
		b.setUsername(currentPrincipalName);
		Blog c=blogRepository.save(b);
		dtoBlog.setId(c.getId());
		
		return dtoBlog; 
	}
	   @Transactional(readOnly = true)
	    public Blog getBlog(String id) {
	        return blogRepository.findById(id)
	                .orElseThrow(() -> new BlogNotFoundException("blog is not found"));
	    }
	   @Transactional(readOnly = true)
	    public List<Blog> getBlogByName(String name) {
	        return blogRepository.findByUsername(name);
//	        orElseThrow(() -> new BlogNotFoundException(name.toString()));
	    }
	@Transactional(readOnly=true)
	public List<Blog> getAll() {
		return blogRepository.findAll();
	}
}