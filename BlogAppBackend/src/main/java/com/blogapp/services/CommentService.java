package com.blogapp.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.constants.Message;
import com.blogapp.dto.request.DtoComment;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.model.Blog;
import com.blogapp.model.Comment;
import com.blogapp.repo.BlogRepository;
import com.blogapp.repo.CommentRepository;
import com.blogapp.repo.UserRepository;

/**
 * This class have methods for posting and fetching comments for blogs
 * 
 * @author Varsha
 * @since 15/03/2021
 */
@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomUserDetails customUserDetails;

	/**
	 * This method post comment for Blogs
	 * 
	 * @param comrequest
	 * @return nothing
	 * @exception ResourceNotFound
	 */
	public void save(DtoComment comrequest) {
		// saving comment for a blog
		Optional<Blog> blog = blogRepository.findById(comrequest.getBlogId());
		Instant instant = Instant.now();
		Comment c = new Comment();
		if (blog.isPresent()) {
			c.setBlogid(blog.get().getId());
			c.setUsername(customUserDetails.getCurrentUser().getUsername());
			c.setComment(comrequest.getComment());
			c.setCreateDate(instant);
			commentRepository.save(c);
		} else {
			throw new ResourceNotFoundException(Message.BLOG_NOT_FOUND);
		}
	}

	/**
	 * Method for fetching all comments for post
	 * 
	 * @param id
	 * @return comment list
	 */
	public List<Comment> getAllCommentsForPost(String id) {
		List<Comment> comment = commentRepository.findByBlogid(id);
		return comment;
	}
}