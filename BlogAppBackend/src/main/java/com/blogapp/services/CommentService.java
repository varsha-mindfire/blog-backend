package com.blogapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

<<<<<<< HEAD:BlogAppBackend/src/main/java/com/blogapp/services/Commentservices.java
import com.blogapp.dto.request.Comment;

import com.blogapp.model.Commentmodel;
import com.blogapp.repo.Commentrepo;
=======
import com.Blogapp.dto.request.DtoComment;
import com.Blogapp.model.Comment;
import com.Blogapp.repo.CommentRepository;
>>>>>>> main:BlogAppBackend/src/main/java/com/blogapp/services/CommentService.java
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
