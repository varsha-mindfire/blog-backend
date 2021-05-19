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

import com.blogapp.constants.Message;
import com.blogapp.dto.request.DtoBlog;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.model.Blog;
import com.blogapp.repo.BlogRepository;
import com.blogapp.services.BlogService;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin("http://localhost:4200")
public class BlogController {
	
	@Autowired
	BlogService blogservice;
	
	//API for creating a new blog
	@Autowired
	BlogRepository blogRepository;
	@RequestMapping(value="createblog",method=RequestMethod.POST)
	public ResponseEntity<MessageResponse> createComment(@RequestBody DtoBlog blogdto) {
		blogservice.saveBlog(blogdto);
		return ResponseEntity.ok(new MessageResponse(Message.BLOG_POSTED));
	}
	
	//API for displaying all blogs
	@GetMapping(value="")
	public ResponseEntity<List<Blog>> getAllBlog() {
	   return  ResponseEntity.status(HttpStatus.OK).body(blogservice.getAll());
	} 
	
	//API for displaying a particular blog
	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	    public ResponseEntity<Blog> getBlogByBlogid(@PathVariable String id) {
	        return ResponseEntity.status(HttpStatus.OK).body(blogservice.getBlog(id));
	}
	
	//API for displaying all blogs of user.
	 @GetMapping("/{name}")
	    public ResponseEntity<List<Blog>> getBlogByUsername(@PathVariable String name) {
	        return ResponseEntity.status(HttpStatus.OK).body(blogservice.getBlogByName(name));
	    }
	 
	//API for updating a  blog
	 @PutMapping({"id/{id}"})
	    public ResponseEntity<MessageResponse> updateTodo(@PathVariable("id") String id, @RequestBody Blog blog) {
		 if( blogservice.updateBlog(id, blog)==true) {
	    	   return ResponseEntity.ok(new MessageResponse(Message.BLOG_UPDATED));
	       }
	       else {
	    	   return ResponseEntity.ok(new MessageResponse(Message.CANNOT_UPDATE_BLOG));
	       }
	    }
	 
	//API for fetching blog by blogid and username
	 @GetMapping("myblogs/{id}")
	    public Blog fetchBlogDetailsByIdAndUsername(@PathVariable String id) {
		 return blogservice.fetchBlog(id);
		}
	 
	//API for deleting a blog
	 @DeleteMapping("deleteblog/{id}")
	 public ResponseEntity<MessageResponse> deleteBlogByIdandUsername(@PathVariable("id") String id){
		 if(blogservice.deleteBlog(id)==true) {
			 return ResponseEntity.ok(new MessageResponse(Message.BLOG_DELETED));
		 }else {
			 return ResponseEntity.ok(new MessageResponse(Message.CANNOT_DELETE_BLOG));
		 } 
	 }

}

