package com.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.request.DtoLike;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.services.LikeService;
@RestController
@RequestMapping("/api/like")
public class LikeController {
	
	@Autowired
	LikeService likeService;
	
	@PostMapping("/likeblog")
	public ResponseEntity<MessageResponse> createLike(@RequestBody DtoLike likedto) {
		likeService.save(likedto);
		return ResponseEntity.ok(new MessageResponse("Like added successfully!"));
	}
}
