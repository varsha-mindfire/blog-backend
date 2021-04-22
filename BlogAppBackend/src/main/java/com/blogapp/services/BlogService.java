package com.blogapp.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogapp.constants.Message;
import com.blogapp.dto.request.DtoBlog;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.model.Blog;
import com.blogapp.model.User;
import com.blogapp.repo.BlogRepository;
import com.blogapp.repo.UserRepository;
@Service
public class BlogService{
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private UserRepository userRepository;	
	public void saveBlog(DtoBlog dtoBlog) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Blog b =new Blog();
		b.setTitle(dtoBlog.getTitle());
		b.setCategory(dtoBlog.getCategory());
		b.setDescription(dtoBlog.getDescription());
		b.setUrl(dtoBlog.getUrl());
		b.setCreateDate(dtoBlog.getCreateDate());
		b.setUsername(currentPrincipalName);
		Optional<User> user=userRepository.findByUsername(currentPrincipalName);
		user.get().setBlogcount(user.get().getBlogcount()+1);
		blogRepository.save(b); 
		userRepository.save(user.get());
	}
	
	   @Transactional(readOnly = true)
	    public Blog getBlog(String id,String name) {
		   Optional<Blog> blog=blogRepository.findTopByIdAndUsernameOrderByIdDesc(id, name);
	        if (!blog.isPresent())
	    		throw new ResourceNotFoundException(Message.BLOG_NOT_FOUND);
	        return blog.get();
	    }
	   
	   @Transactional(readOnly = true)
	    public List<Blog> getBlogByName(String name) {
		   List<Blog> emptylst = Collections.emptyList();
		   List<Blog> blog =blogRepository.findByUsername(name);
		      if (blog.isEmpty())
		    	  return emptylst;
		      else {
		        return blog;
		      }
	    }
	   
		@Transactional(readOnly=true)
		public List<Blog> getAll() {
			return blogRepository.findAll();
		}
		
		public void updateBlog(String id, Blog blog) {
	        Optional<Blog> BlogFromDb = blogRepository.findById(id);
	        if(BlogFromDb.isPresent()) {
	        		Blog blogdb=BlogFromDb.get();
		        	blogdb.setTitle(blog.getTitle());
		 	        blogdb.setCategory(blog.getCategory());
		 	        blogdb.setDescription(blog.getDescription());
		 	        blogdb.setCreateDate(blog.getCreateDate());
		 	        blogRepository.save(blogdb);
	        }
	        
	    }
}