package com.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD:BlogAppBackend/src/main/java/com/blogapp/controller/Commentcontroller.java
import com.blogapp.dto.request.Comment;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.repo.Commentrepo;
import com.blogapp.services.Commentservices;
=======

import com.Blogapp.dto.request.DtoComment;
import com.Blogapp.dto.response.MessageResponse;
import com.Blogapp.repo.CommentRepository;
import com.Blogapp.services.CommentService;

>>>>>>> main:BlogAppBackend/src/main/java/com/blogapp/controller/CommentController.java

@RestController
@RequestMapping("/api/auth")
public class CommentController {
	@Autowired
	CommentService commentservice;
	@Autowired
	CommentRepository commentRepository;
	@RequestMapping(value="createcomment",method=RequestMethod.POST)
	public ResponseEntity<?> createStudent(@RequestBody DtoComment commentdto) {
		commentservice.save(commentdto);
		return ResponseEntity.ok(new MessageResponse("Comment added successfully!"));
	}
}
