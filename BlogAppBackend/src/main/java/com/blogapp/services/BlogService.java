package com.blogapp.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogapp.constants.Message;
import com.blogapp.dto.request.DtoBlog;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.model.Blog;
import com.blogapp.model.User;
import com.blogapp.repo.BlogRepository;
import com.blogapp.repo.CommentRepository;
import com.blogapp.repo.LikeRepository;
import com.blogapp.repo.UserRepository;

/**
 * This class have methods for creating,fetching,updating and deleting blogs
 * 
 * @author Varsha
 * @since 15/03/2021
 */
@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomUserDetails customUserDetails;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	FileService fileservice;

	/**
	 * This method is used for saving blog information from request
	 * 
	 * @param dtoBlog
	 * @return void
	 */
	public void saveBlog(DtoBlog dtoBlog) {
		// creating new blog
		Blog blog = new Blog();
		blog.setTitle(dtoBlog.getTitle());
		blog.setCategory(dtoBlog.getCategory());
		blog.setDescription(dtoBlog.getDescription());
		blog.setUrl(dtoBlog.getUrl());
		blog.setName(dtoBlog.getName());
		blog.setCreateDate(dtoBlog.getCreateDate());
		blog.setPath(dtoBlog.getPath());
		blog.setUsername(customUserDetails.getCurrentUser().getUsername());
		Optional<User> user = userRepository.findByUsername(customUserDetails.getCurrentUser().getUsername());
		user.get().setBlogcount(user.get().getBlogcount() + 1);
		blogRepository.save(blog);
		userRepository.save(user.get());
	}

	/**
	 * Method for retrieving blog by blogid
	 * 
	 * @param id
	 * @return blog object
	 */

	public Blog getBlog(String id) {
		// getting blog details using blogId
		Optional<Blog> blog = blogRepository.findById(id);
		if (!blog.isPresent())
			throw new ResourceNotFoundException(Message.BLOG_NOT_FOUND);
		return blog.get();
	}

	/**
	 * Method for fetching blogs by username
	 * 
	 * @param name
	 * @return blog list
	 */
	public List<Blog> getBlogByName(String name) {
		List<Blog> emptylst = Collections.emptyList();
		// getting blog details using username
		List<Blog> blog = blogRepository.findByUsername(name);
		if (blog.isEmpty()) {
			return emptylst;
		} else {
			return blog;
		}
	}

	/**
	 * Method for fetching all blogs
	 * 
	 * @return blog list
	 */
	@Transactional(readOnly = true)
	public List<Blog> getAll() {
		// fetching list of blogs
		return blogRepository.findAllByOrderByCreateDateDesc();
	}

	/**
	 * Method for updating blogs
	 * 
	 * @param id
	 * @param blog
	 */
	public void updateBlog(String id, Blog blog) {
		try {
			Optional<Blog> BlogFromDb = blogRepository.findById(id);
			String name = customUserDetails.getCurrentUser().getUsername();
			if (!BlogFromDb.get().getUsername().equals(name)) {
				throw new ResourceNotFoundException(Message.CANNOT_UPDATE_BLOG);
			} else {
				// updating blog details
				Blog blogdb = BlogFromDb.get();
				blogdb.setTitle(blog.getTitle());
				blogdb.setCategory(blog.getCategory());
				blogdb.setDescription(blog.getDescription());
				blogdb.setCreateDate(blog.getCreateDate());
				blogRepository.save(blogdb);
			}
		} catch (ResourceNotFoundException resourceNotFoundException) {
			throw new ResourceNotFoundException(Message.CANNOT_UPDATE_BLOG);

		}
	}

	public Blog fetchBlog(String id) {
		Blog b = blogRepository.findByIdAndUsername(id, customUserDetails.getCurrentUser().getUsername());
		return b;
	}

	/**
	 * Method for deleting blog by blogId
	 * 
	 * @param id
	 * @return boolean
	 */
	public void deleteBlog(String id) {
		try {
			Optional<Blog> b = blogRepository.findById(id);
			String p = customUserDetails.getCurrentUser().getUsername();
			Optional<User> user = userRepository.findByUsername(customUserDetails.getCurrentUser().getUsername());
			if (!b.get().getUsername().equals(p)) {
				throw new ResourceNotFoundException(Message.CANNOT_DELETE_BLOG);

			} else {
				// deleting blog details
				blogRepository.deleteByIdAndUsername(id, customUserDetails.getCurrentUser().getUsername());
				commentRepository.deleteByBlogid(id);
				likeRepository.deleteByBlogId(id);
				user.get().setBlogcount(user.get().getBlogcount() - 1);
				userRepository.save(user.get());
			}

		} catch (ResourceNotFoundException resourceNotFoundException) {
			throw new ResourceNotFoundException(Message.CANNOT_DELETE_BLOG);

		}

	}
}