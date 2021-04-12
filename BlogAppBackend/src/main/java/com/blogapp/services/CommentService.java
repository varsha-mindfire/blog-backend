package com.blogapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blogapp.dto.request.DtoComment;
import com.blogapp.model.Blog;
import com.blogapp.model.Comment;
import com.blogapp.repo.BlogRepository;
import com.blogapp.repo.CommentRepository;
@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	BlogRepository blogRepository;
	public void save(DtoComment comrequest) {
		Optional<Blog> blog=blogRepository.findById(comrequest.getBlogId());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object currentPrincipalName = authentication.getDetails();
		String name=authentication.getName();
		Comment c = new Comment();
		c.setComment(comrequest.getComment());
		c.setDetails(currentPrincipalName);
		c.setUsername(name);
		commentRepository.save(c);
	}
}