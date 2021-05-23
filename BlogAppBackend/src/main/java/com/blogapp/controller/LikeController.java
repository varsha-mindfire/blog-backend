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

import com.blogapp.constants.Emessage;
import com.blogapp.dto.request.DtoLike;
import com.blogapp.dto.response.EmessageResponse;
import com.blogapp.services.LikeService;

/**
 * This class contains operations performed on blogs
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */
@RestController
@RequestMapping("/api/like")
@CrossOrigin("http://localhost:4200")
public class LikeController {

	@Autowired
	LikeService likeService;

	/**
	 * This Method is responsible for liking a blog
	 * 
	 * @param DtoLike
	 * @return MessageResponse
	 */
	@PostMapping("/likeblog")
	public ResponseEntity<EmessageResponse> createLike(@RequestBody DtoLike likedto) {
		Integer like = likeService.save(likedto);
		if (like == 1) {
			return ResponseEntity.ok(new EmessageResponse(Emessage.LIKE_ADDED));
		} else {
			return ResponseEntity.ok(new EmessageResponse(Emessage.LIKE_REMOVED));
		}
	}

	/**
	 * Method responsible for checking whether a user has already liked a post or not
	 * 
	 * @param blogId
	 * @return Boolean
	 */
	@GetMapping("/likeblog/{blogId}")
	public Boolean fetchLikeDetails(@PathVariable String blogId) {
		return likeService.fetchDetail(blogId);
	}
}
