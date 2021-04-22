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

import com.blogapp.dto.request.DtoLike;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.services.LikeService;
@RestController
@RequestMapping("/api/like")
@CrossOrigin("http://localhost:4200")
public class LikeController {
	
	@Autowired
	LikeService likeService;
	
	@PostMapping("/likeblog")
	public ResponseEntity<MessageResponse> createLike(@RequestBody DtoLike likedto) {
		Integer p=likeService.save(likedto);
		if(p==1) {
		return ResponseEntity.ok(new MessageResponse("Like added successfully!"));
	}
		else {
			return ResponseEntity.ok(new MessageResponse("Like removed successfully!"));
		}
			
		}
	@GetMapping("/likeblog/{username}/{blogId}")
	public Boolean fetchLikeDetails(@PathVariable String blogId, @PathVariable String username) {
		if(likeService.fetchDetail(blogId,username)) {
			return true;
		}
		else {
			return false;
			
		}
//		@GetMapping("/likeblog")
//		public ResponseEntity<MessageResponse> fetchLikeDetails(String blogId,String username) {
//			if(likeService.fetchDetail(blogId,username)==true) {
//				return ResponseEntity.ok(new MessageResponse("details fetched"));
//			}
//			else {
//				return ResponseEntity.ok(new MessageResponse("no details available"));
//				
//			}
//	}
}
}
