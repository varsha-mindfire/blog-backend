package com.Blogapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Blogapp.dto.request.Comment;

import com.Blogapp.model.Commentmodel;
import com.Blogapp.repo.Commentrepo;
@Service
public class Commentservices {
	@Autowired
	Commentrepo commentrepo;
	public void save(Comment comrequest) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object currentPrincipalName = authentication.getDetails();
		String name=authentication.getName();
		Commentmodel c = new Commentmodel();
		c.setComment(comrequest.getComment());
//		c.setBlogid(currentPrincipalName);
//		b.setCreateDate(blogdto.getCreateDate());
		c.setDetails(currentPrincipalName);
		c.setUsername(name);
		commentrepo.save(c);
	}

}
