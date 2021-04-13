package com.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.request.DtoComment;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.model.Blog;
import com.blogapp.model.Comment;
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
	public ResponseEntity<MessageResponse> createComment(@RequestBody DtoComment commentdto) {
		commentservice.save(commentdto);
		return ResponseEntity.ok(new MessageResponse("Comment added successfully!"));
	}
	 @GetMapping("/by-blog/{id}")
	    public ResponseEntity<List<Comment>> getBlogByBlogid(@PathVariable String id) {
	        return ResponseEntity.status(HttpStatus.OK).body(commentservice.getAllCommentsForPost(id));
	    }
	 @GetMapping("by-user/{username}")
	    public ResponseEntity<List<Comment>> getBlogByUsername(@PathVariable String username) {
	        return ResponseEntity.status(HttpStatus.OK).body(commentservice.getAllCommentsForUser(username));
	    }}
