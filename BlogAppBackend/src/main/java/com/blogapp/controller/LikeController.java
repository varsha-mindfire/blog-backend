package com.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.constants.Message;
import com.blogapp.dto.request.DtoLike;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.services.LikeService;
@RestController
@RequestMapping("/api/like")
@CrossOrigin("http://localhost:4200")
public class LikeController {
	
	@Autowired
	LikeService likeService;
	
	//API for liking a post
	@PostMapping("/likeblog")
	public ResponseEntity<MessageResponse> createLike(@RequestBody DtoLike likedto) {
		Integer p=likeService.save(likedto);
		if(p==1) 
		{
		return ResponseEntity.ok(new MessageResponse(Message.LIKE_ADDED));
		}
		else 
		{
			return ResponseEntity.ok(new MessageResponse(Message.LIKE_REMOVED));
		}
	}
	
	//API for checking whether a user has already liked a post or not
	@GetMapping("/likeblog/{blogId}")
	public Boolean fetchLikeDetails(@PathVariable String blogId)
	{
		if(likeService.fetchDetail(blogId)==true)
		{
			return true;
		}
		else 
		{
			return false;
			
		}
	}
}

