package com.blogapp.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blogapp.model.Blog;
public interface BlogRepository extends MongoRepository<Blog, String> {

	Optional<Blog> findByTitle(String title);
	Optional<Blog> findById(String id);
}
