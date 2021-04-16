package com.blogapp.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogapp.dto.request.DtoComment;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.model.Blog;
import com.blogapp.model.Comment;
import com.blogapp.repo.BlogRepository;
import com.blogapp.repo.CommentRepository;
import com.blogapp.repo.UserRepository;
@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void save(DtoComment comrequest) {
	Optional<Blog> blog=blogRepository.findById(comrequest.getBlogId());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name=authentication.getName();
		Comment c = new Comment();
		
		if(blog.isPresent()) {
			Blog b=blog.get();
			String id=b.getId();
			c.setBlogid(id);
			c.setUsername(name);
			c.setComment(comrequest.getComment());
		}
		
		commentRepository.save(c);
	}
	
	   @Transactional(readOnly = true)
	    public List<Comment> getAllCommentsForPost(String id) {
	        List<Comment> comment= commentRepository.findByBlogid(id);
	        if(comment.isEmpty()) {
	        	throw new ResourceNotFoundException("No comments found");
	        }
	        return comment;
	    }
	   
	   @Transactional(readOnly = true)
	    public List<Comment> getAllCommentsForUser(String name) {
		   List<Comment> emptylst = Collections.emptyList();
		   List<Comment> comment =commentRepository.findByUsername(name);
		      if (comment.isEmpty()) {

		    	  return emptylst;
		      }		    		
		      return comment;  
	    }
}