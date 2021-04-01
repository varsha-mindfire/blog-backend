package com.Blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Blogapp.dto.request.Blogdto;
import com.Blogapp.dto.response.MessageResponse;
import com.Blogapp.model.Blog;
import com.Blogapp.repo.Blogrepo;
import com.Blogapp.services.Blogservice;

@RestController
@RequestMapping("/api/test")
public class Blogcontroller {
	@Autowired 
	Blogservice blogservice;
	@Autowired
	Blogrepo blogrepo;
//	@PostMapping
//	public void createBlog(@RequestBody Blogdto blogdto) {
//		
//	}
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@RequestMapping(value="user/createresource",method=RequestMethod.POST)
	public ResponseEntity<?> createStudent(@RequestBody Blogdto blogdto) {
		blogservice.save(blogdto);
		return ResponseEntity.ok(new MessageResponse("Blog posted successfully!"));
	}
	@GetMapping(value="/getall")
	public List<Blog> getAll() {
	    return blogrepo.findAll();
	}
//	@GetMapping(value = "/{username}")
//	public List<Blog> getOne(HttpServletRequest request) {
//		Principal principal = request.getUserPrincipal();
//	    return blogrepo.findByUsername(principal.getName());
//	    
////	    		.orElseThrow(() -> new ResourceNotFoundException());
//	}
}