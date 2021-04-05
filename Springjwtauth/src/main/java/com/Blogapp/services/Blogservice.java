package com.Blogapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Blogapp.dto.request.DtoBlog;
import com.Blogapp.model.Blog;
import com.Blogapp.repo.BlogRepository;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
//@Builder
public class Blogservice{
	@Autowired
	private BlogRepository blogRepository;
	
	public void save(DtoBlog dtoBlog) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Blog b =new Blog();
		b.setTitle(dtoBlog.getTitle());
		b.setSection(dtoBlog.getSection());
		b.setDescription(dtoBlog.getDescription());
		b.setCreateDate(dtoBlog.getCreateDate());
		b.setUsername(currentPrincipalName);
		blogRepository.save(b);
	}
	


}
