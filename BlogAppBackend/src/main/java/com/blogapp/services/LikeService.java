package com.blogapp.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.dto.request.DtoLike;
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
	
	@Autowired
	CustomUserDetails customUserDetails;
	
	public Integer save(DtoLike likerequest) {
		Optional<Blog> blog=blogRepository.findById(likerequest.getBlogId());
		Blog blog1=blog.get();
		Like like=new Like();
		Integer C=0;
		Optional<Like> voteByBlogAndUser = likeRepository.findTopByBlogIdAndUsernameOrderByIdDesc(blog1.getId(), customUserDetails.getCurrentUser().getUsername());
		if(likerequest.getLike()==1 && !voteByBlogAndUser.isPresent()){
			Instant instant = Instant.now();
			blog1.setLikeCount(blog1.getLikeCount()+1);
			like.setLike(likerequest.getLike());
			like.setBlogId(blog1.getId());
			like.setUsername(customUserDetails.getCurrentUser().getUsername());
			like.setCreateDate(instant);
			likeRepository.save(like);
			blogRepository.save(blog1);
			C+=1;
			}
		else if(likerequest.getLike()==1 && voteByBlogAndUser.isPresent()) {
			blog1.setLikeCount(blog1.getLikeCount()-1);
			likeRepository.deleteByUsername(customUserDetails.getCurrentUser().getUsername());
			blogRepository.save(blog1);
			C-=1;
		}
		return C;
			
	}
	public Boolean fetchDetail(String blogId) {
		Optional<Like> det=likeRepository.findByBlogIdAndUsername(blogId,customUserDetails.getCurrentUser().getUsername());
		if(det.isPresent()) {
			return true;

		}
		else {
			return false;
		}
	}
}
