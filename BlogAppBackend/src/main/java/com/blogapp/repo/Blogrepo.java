package com.blogapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blogapp.model.Blog;
public interface Blogrepo extends MongoRepository<Blog, String> {
	Optional<Blog> findByTitle(String title);
	Optional<Blog> findById(String id);
//	List<Blog> findByUsername(String username);
}