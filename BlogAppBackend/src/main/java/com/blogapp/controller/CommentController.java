package com.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.request.DtoComment;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.repo.CommentRepository;
import com.blogapp.services.CommentService;


@RestController
@RequestMapping("/comments")
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