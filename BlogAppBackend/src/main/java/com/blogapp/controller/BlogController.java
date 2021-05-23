package com.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.constants.Emessage;
import com.blogapp.dto.request.DtoBlog;
import com.blogapp.dto.response.EmessageResponse;
import com.blogapp.model.Blog;
import com.blogapp.repo.BlogRepository;
import com.blogapp.services.BlogService;

/**
 * This file specifies all methods of a blog
 * 
 * @author Varsha
 * @since 15/02/2021
 * 
 */
@RestController
@RequestMapping("/api/blogs")
@CrossOrigin("http://localhost:4200")
public class BlogController {

	@Autowired
	BlogService blogService;

	@Autowired
	BlogRepository blogRepository;

	/**
	 * Method responsible for creating a blog
	 * 
	 * @param DtoBlog
	 * @return ResponseMessage
	 */
	@RequestMapping(value = "createblog", method = RequestMethod.POST)
	public ResponseEntity<EmessageResponse> createComment(@RequestBody DtoBlog blogdto) {
		blogService.saveBlog(blogdto);
		return ResponseEntity.ok(new EmessageResponse(Emessage.BLOG_POSTED));
	}

	/**
	 * Method responsible for displaying all blogs
	 * 
	 * @return List of Blogs
	 */
	@GetMapping(value = "")
	public ResponseEntity<List<Blog>> getAllBlog() {
		return ResponseEntity.status(HttpStatus.OK).body(blogService.getAll());
	}

	/**
	 * Method responsible for displaying a particular blog
	 * 
	 * @param id id of the blog
	 * @return blog
	 */
	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Blog> getBlogByBlogid(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlog(id));
	}

	/**
	 * Method responsible for displaying all blogs of user.
	 * 
	 * @param name, current logged in user's username
	 * @return List of Blog
	 */
	@GetMapping("/{name}")
	public ResponseEntity<List<Blog>> getBlogByUsername(@PathVariable String name) {
		return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogByName(name));
	}

	/**
	 * Method responsible for updating a blog
	 * 
	 * @param id
	 * @param blog
	 * @return ResponseMessage
	 */
	@PutMapping({ "id/{id}" })
	public ResponseEntity<EmessageResponse> updateTodo(@PathVariable("id") String id, @RequestBody Blog blog) {
		blogService.updateBlog(id, blog);
		return ResponseEntity.ok(new EmessageResponse(Emessage.BLOG_UPDATED));
	}

	/**
	 * Method responsible for fetching blog by blogid and username
	 * 
	 * @param id username
	 * @return
	 */
	@GetMapping("myblogs/{id}")
	public Blog fetchBlogDetailsByIdAndUsername(@PathVariable String id) {
		return blogService.fetchBlog(id);
	}

	/**
	 * Method responsible for deleting a blog
	 * 
	 * @param id
	 * @return ResponseMessage
	 */
	@DeleteMapping("deleteblog/{id}")
	public ResponseEntity<EmessageResponse> deleteBlogByIdandUsername(@PathVariable("id") String id) {
		blogService.deleteBlog(id);
		return ResponseEntity.ok(new EmessageResponse(Emessage.BLOG_DELETED));
	}
}