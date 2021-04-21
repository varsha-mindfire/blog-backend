package com.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.request.DtoBlog;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.model.Blog;
import com.blogapp.repo.BlogRepository;
import com.blogapp.services.BlogService;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin("http://localhost:4200")
public class BlogController {
	
	@Autowired
	BlogService blogservice;
	
	@Autowired
	BlogRepository blogRepository;
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@RequestMapping(value="createblog",method=RequestMethod.POST)
	public ResponseEntity<MessageResponse> createComment(@RequestBody DtoBlog blogdto) {
		blogservice.saveBlog(blogdto);
		return ResponseEntity.ok(new MessageResponse("Blog added successfully!"));
	}
	
	@GetMapping(value="")
	public ResponseEntity<List<Blog>> getAllBlog() {
	   return  ResponseEntity.status(HttpStatus.OK).body(blogservice.getAll());
	}  

	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)

	    public ResponseEntity<Blog> getBlogByBlogid(@PathVariable String id) {
	        return ResponseEntity.status(HttpStatus.OK).body(blogservice.getBlog(id));
		
	}
	
	 @GetMapping("/{name}")
	    public ResponseEntity<List<Blog>> getBlogByUsername(@PathVariable String name) {
	        return ResponseEntity.status(HttpStatus.OK).body(blogservice.getBlogByName(name));
	    }
	 
	 @PutMapping({"id/{id}"})
	    public ResponseEntity<Blog> updateTodo(@PathVariable("id") String id, @RequestBody Blog blog) {
	        blogservice.updateBlog(id, blog);
	        return new ResponseEntity<>(blogservice.getBlog(id), HttpStatus.OK);
	    }
//	 @PutMapping({"id/{username}"})
//	    public ResponseEntity<Blog> updateTodo(@PathVariable("username") String username, @RequestBody Blog blog) {
//	        blogservice.updateBlog(username, blog);
//	        return new ResponseEntity<>(blogservice.getBlog(id), HttpStatus.OK);
//	    }
	 }


