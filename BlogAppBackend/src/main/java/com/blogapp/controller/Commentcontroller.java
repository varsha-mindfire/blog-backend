package com.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.blogapp.dto.request.Comment;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.repo.Commentrepo;
import com.blogapp.services.Commentservices;

@RestController
@RequestMapping("/api/auth")
public class Commentcontroller {
	@Autowired
	Commentservices commentservice;
	@Autowired
	Commentrepo commentrepo;
	@RequestMapping(value="createcomment",method=RequestMethod.POST)
	public ResponseEntity<?> createStudent(@RequestBody Comment commentdto) {
		commentservice.save(commentdto);
		return ResponseEntity.ok(new MessageResponse("Comment added successfully!"));
	}
}
