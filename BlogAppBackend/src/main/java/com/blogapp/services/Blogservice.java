package com.blogapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

<<<<<<< HEAD:BlogAppBackend/src/main/java/com/blogapp/services/Blogservice.java
import com.blogapp.dto.request.Blogdto;
import com.blogapp.model.Blog;
import com.blogapp.repo.Blogrepo;
=======
import com.Blogapp.dto.request.DtoBlog;
import com.Blogapp.model.Blog;
import com.Blogapp.repo.BlogRepository;

>>>>>>> main:Springjwtauth/src/main/java/com/Blogapp/services/Blogservice.java

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
