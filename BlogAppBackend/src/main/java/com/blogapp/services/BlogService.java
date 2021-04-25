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
@Service
public class BlogService{
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
	public void saveBlog(DtoBlog dtoBlog) {
		Blog b =new Blog();
		b.setTitle(dtoBlog.getTitle());
		b.setCategory(dtoBlog.getCategory());
		b.setDescription(dtoBlog.getDescription());
		b.setUrl(dtoBlog.getUrl());
		b.setCreateDate(dtoBlog.getCreateDate());
		b.setUsername(customUserDetails.getCurrentUser().getUsername());
		Optional<User> user=userRepository.findByUsername(customUserDetails.getCurrentUser().getUsername());
		user.get().setBlogcount(user.get().getBlogcount()+1);
		blogRepository.save(b); 
		userRepository.save(user.get());
	}
	
	   @Transactional(readOnly = true)
	    public Blog getBlog(String id) {
		   Optional<Blog> blog=blogRepository.findById(id);
	        if (!blog.isPresent())
	    		throw new ResourceNotFoundException(Message.BLOG_NOT_FOUND);
	        return blog.get();
	    }
	   
	   @Transactional(readOnly = true)
	    public List<Blog> getBlogByName(String name) {
		   List<Blog> emptylst = Collections.emptyList();
		   List<Blog> blog =blogRepository.findByUsername(name);
		      if (blog.isEmpty())
		    	  return emptylst;
		      else {
		        return blog;
		      }
	    }
	   
		@Transactional(readOnly=true)
		public List<Blog> getAll() {
			return blogRepository.findAll();
		}
		
		public boolean updateBlog(String id, Blog blog) {
	        Optional<Blog> BlogFromDb = blogRepository.findById(id);
	        String p=customUserDetails.getCurrentUser().getUsername();
	        if(BlogFromDb.get().getUsername().equals(p)) {
	        		Blog blogdb=BlogFromDb.get();
		        	blogdb.setTitle(blog.getTitle());
		 	        blogdb.setCategory(blog.getCategory());
		 	        blogdb.setDescription(blog.getDescription());
		 	        blogdb.setCreateDate(blog.getCreateDate());
		 	        blogRepository.save(blogdb);
		 	        return true;
	        }
	        else {
	        	return false;
	        }
	        
	    }
		public Blog fetchBlog(String id) {
			Blog b=blogRepository.findByIdAndUsername(id,customUserDetails.getCurrentUser().getUsername());
			return b;
		}
		public boolean deleteBlog(String id) {
			Optional<Blog> b = blogRepository.findById(id);
			String p=customUserDetails.getCurrentUser().getUsername();
			Optional<User> user= userRepository.findByUsername(customUserDetails.getCurrentUser().getUsername());
			if(b.get().getUsername().equals(p))
			{
				blogRepository.deleteByIdAndUsername(id,customUserDetails.getCurrentUser().getUsername());
				commentRepository.deleteByBlogid(id);
				likeRepository.deleteByBlogId(id);
				user.get().setBlogcount(user.get().getBlogcount()-1);
				userRepository.save(user.get());
				return true;
			}
			else
			{
				return false;
			}
			
		}
}