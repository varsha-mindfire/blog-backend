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
import com.blogapp.repo.BlogRepository;
@Service
public class BlogService{
	@Autowired
	private BlogRepository blogRepository;
	
	public void saveBlog(DtoBlog dtoBlog) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Blog b =new Blog();
		b.setTitle(dtoBlog.getTitle());
		b.setSection(dtoBlog.getSection());
		b.setDescription(dtoBlog.getDescription());
		b.setUsername(currentPrincipalName);
//		b.setLikeCount(0);
		blogRepository.save(b); 
	}
	   @Transactional(readOnly = true)
	    public Blog getBlog(String id) {
	        Optional<Blog> blog=blogRepository.findById(id);
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
		        return blog;
	    }
	@Transactional(readOnly=true)
	public List<Blog> getAll() {
		return blogRepository.findAll();
	}
	public void updateBlog(String id, Blog blog) {
        Blog BlogFromDb = blogRepository.findById(id).get();
        BlogFromDb.setTitle(blog.getTitle());
        BlogFromDb.setSection(blog.getSection());
        BlogFromDb.setDescription(blog.getDescription());
        blogRepository.save(BlogFromDb);
    }
}