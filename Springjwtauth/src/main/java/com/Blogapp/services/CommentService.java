package com.Blogapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Blogapp.dto.request.DtoComment;
import com.Blogapp.model.Comment;
import com.Blogapp.repo.CommentRepository;
@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;
	public void save(DtoComment comrequest) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object currentPrincipalName = authentication.getDetails();
		String name=authentication.getName();
		Comment c = new Comment();
		c.setComment(comrequest.getComment());
//		c.setBlogid(currentPrincipalName);
//		b.setCreateDate(blogdto.getCreateDate());
		c.setDetails(currentPrincipalName);
		c.setUsername(name);
		commentRepository.save(c);
	}

}
