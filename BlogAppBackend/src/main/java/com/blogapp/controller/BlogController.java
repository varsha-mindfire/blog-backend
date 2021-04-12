package com.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.request.DtoBlog;
import com.blogapp.model.Blog;
import com.blogapp.repo.BlogRepository;
import com.blogapp.services.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	BlogService blogservice;
	
	@Autowired
	BlogRepository blogRepository;
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@RequestMapping(value="createblog",method=RequestMethod.POST)
	public void createBlog (@RequestBody DtoBlog dtoBlog) {
		ResponseEntity.status(HttpStatus.CREATED ).body(blogservice.saveBlog(dtoBlog));
	}
	
	@GetMapping(value="/getall")
	public ResponseEntity<List<Blog>> getAllBlog() {
	   return  ResponseEntity.status(HttpStatus.OK).body(blogservice.getAll());
	}  
}