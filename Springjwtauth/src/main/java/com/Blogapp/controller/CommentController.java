package com.Blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Blogapp.dto.request.DtoComment;
import com.Blogapp.dto.response.MessageResponse;
import com.Blogapp.repo.CommentRepository;
import com.Blogapp.services.CommentService;


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
