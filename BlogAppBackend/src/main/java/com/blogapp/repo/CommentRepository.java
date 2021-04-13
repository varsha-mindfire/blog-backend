package com.blogapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blogapp.model.Blog;
import com.blogapp.model.Comment;
import com.blogapp.model.User;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
	List<Comment> findByUsername(String id);
	List<Comment> findByBlogid(String id);


}