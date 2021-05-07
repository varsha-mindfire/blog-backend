package com.blogapp.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.blogapp.model.Comment;

//Comment repository interface
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
	
	List<Comment> findByUsername(String id);
	List<Comment> findByBlogid(String id);
	void deleteByBlogid(String blogId);

}