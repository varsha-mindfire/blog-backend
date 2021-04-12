package com.blogapp.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blogapp.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
	Optional<Comment> findById(String id);
}