package com.Blogapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Blogapp.dto.request.Blogdto;
import com.Blogapp.model.Blog;
import com.Blogapp.repo.Blogrepo;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Service
@AllArgsConstructor
@Builder
public class Blogservice{
	@Autowired
	private Blogrepo blogrepo;
	
	public void save(Blogdto blogdto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Blog b =new Blog();
		b.setTitle(blogdto.getTitle());
		b.setSection(blogdto.getSection());
		b.setDescription(blogdto.getDescription());
		b.setCreateDate(blogdto.getCreateDate());
		b.setUsername(currentPrincipalName);
		blogrepo.save(b);
	}
	


}
