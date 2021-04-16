package com.blogapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.blogapp.dto.request.DtoLike;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.model.Blog;
import com.blogapp.model.Like;
import com.blogapp.repo.BlogRepository;
import com.blogapp.repo.LikeRepository;
@Service
public class LikeService {
	Integer count=0;
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private LikeRepository likeRepository;
	public void save(DtoLike likerequest) {
		Optional<Blog> blog=blogRepository.findById(likerequest.getBlogId());
		Blog blog1=blog.get();
		Like like=new Like();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name=authentication.getName();
		Optional<Like> voteByBlogAndUser = likeRepository.findTopByBlogIdAndUsernameOrderByIdDesc(blog1.getId(), name);
		if (voteByBlogAndUser.isPresent()) {
			throw new ResourceNotFoundException("you already voted");
		}
		if(likerequest.getLike()==1) {
			blog1.setLikeCount(blog1.getLikeCount()+1);
			like.setLike(likerequest.getLike());
			like.setBlogId(blog1.getId());
			like.setUsername(name);
        	
		}
			likeRepository.save(like);
			blogRepository.save(blog1);
			
	}
}