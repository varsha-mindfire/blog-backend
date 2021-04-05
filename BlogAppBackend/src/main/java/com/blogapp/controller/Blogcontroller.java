package com.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD:BlogAppBackend/src/main/java/com/blogapp/controller/Blogcontroller.java
import com.blogapp.dto.request.Blogdto;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.model.Blog;
import com.blogapp.repo.Blogrepo;
import com.blogapp.services.Blogservice;
=======
import com.Blogapp.dto.request.DtoBlog;
import com.Blogapp.dto.response.MessageResponse;
import com.Blogapp.model.Blog;
import com.Blogapp.repo.BlogRepository;
import com.Blogapp.services.Blogservice;
>>>>>>> main:BlogAppBackend/src/main/java/com/blogapp/controller/BlogController.java

@RestController
@RequestMapping("/api/test")
public class BlogController {
	@Autowired 
	Blogservice blogservice;
	@Autowired
	BlogRepository blogRepository;
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@RequestMapping(value="user/createresource",method=RequestMethod.POST)
	public ResponseEntity<?> createStudent(@RequestBody DtoBlog dtoBlog) {
		blogservice.save(dtoBlog);
		return ResponseEntity.ok(new MessageResponse("Blog posted successfully!"));
	}
	@GetMapping(value="/getall")
	public List<Blog> getAll() {
	    return blogRepository.findAll();
	}
}
