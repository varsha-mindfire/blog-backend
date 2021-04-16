package com.blogapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogapp.dto.request.DtoComment;
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
	        return commentRepository.findByBlogid(id);
//	                .orElseThrow(() -> new BlogNotFoundException("blog is not found"));
	    }
	   @Transactional(readOnly = true)
	    public List<Comment> getAllCommentsForUser(String name) {
	
	        return commentRepository.findByUsername(name);
//	                .orElseThrow(() -> new BlogNotFoundException(name.toString()));
	    }
}