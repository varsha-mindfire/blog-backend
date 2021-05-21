package com.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.constants.Emessage;
import com.blogapp.dto.request.DtoComment;
import com.blogapp.dto.response.EmessageResponse;
import com.blogapp.model.Comment;
import com.blogapp.repo.CommentRepository;
import com.blogapp.services.CommentService;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin("http://localhost:4200")
public class CommentController {
	@Autowired
	CommentService commentService;

	@Autowired
	CommentRepository commentRepository;

	// API for posting comments in a particular blog
	@PostMapping("/createcomment")
	public ResponseEntity<EmessageResponse> createComment(@RequestBody DtoComment commentdto) {
		commentService.save(commentdto);
		return ResponseEntity.ok(new EmessageResponse(Emessage.COMMENT_ADDED));
	}

	// API for fetching all comments for a particular blog.
	@GetMapping("/id/{id}")
	public ResponseEntity<List<Comment>> getCommentsByBlogid(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForPost(id));
	}
}
