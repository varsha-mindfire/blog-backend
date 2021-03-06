package com.blogapp.services;

import java.time.Instant;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.constants.Message;
import com.blogapp.dto.request.DtoLike;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.model.Blog;
import com.blogapp.model.Like;
import com.blogapp.repo.BlogRepository;
import com.blogapp.repo.LikeRepository;

/**
 * This class contains method for liking a post
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */
@Service
public class LikeService {
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	CustomUserDetails customUserDetails;

	/**
	 * Method for saving likes by user for a particular blog
	 * 
	 * @param likerequest
	 * @return Integer
	 */
	public Integer save(DtoLike likerequest) {
		Integer C = 0;
		Optional<Blog> blog = blogRepository.findById(likerequest.getBlogId());
		blog.orElseThrow(() -> new ResourceNotFoundException(Message.BLOG_NOT_FOUND));
		Blog blog1 = blog.get();
		Like like = new Like();

		Optional<Like> voteByBlogAndUser = likeRepository.findTopByBlogIdAndUsernameOrderByIdDesc(blog1.getId(),
				customUserDetails.getCurrentUser().getUsername());
		if (likerequest.getLike() == 1 && !voteByBlogAndUser.isPresent()) {
			Instant instant = Instant.now();
			blog1.setLikeCount(blog1.getLikeCount() + 1);
			like.setLike(likerequest.getLike());
			like.setBlogId(blog1.getId());
			like.setUsername(customUserDetails.getCurrentUser().getUsername());
			like.setCreateDate(instant);
			likeRepository.save(like);
			blogRepository.save(blog1);
			C += 1;
		} else if (likerequest.getLike() == 1 && voteByBlogAndUser.isPresent()) {
			blog1.setLikeCount(blog1.getLikeCount() - 1);
			likeRepository.deleteByUsernameAndBlogId(customUserDetails.getCurrentUser().getUsername(), blog1.getId());
			blogRepository.save(blog1);
			C -= 1;
		}
		return C;
	}

	/**
	 * Method to check whether a user has already liked a blog or not.
	 * 
	 * @param blogId
	 * @return Boolean
	 */
	public Boolean fetchDetail(String blogId) {
		Optional<Like> details = likeRepository.findByBlogIdAndUsername(blogId,
				customUserDetails.getCurrentUser().getUsername());
		if (details.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
}
