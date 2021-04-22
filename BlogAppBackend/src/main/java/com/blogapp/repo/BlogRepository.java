package com.blogapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blogapp.model.Blog;
public interface BlogRepository extends MongoRepository<Blog, String> {

	List<Blog> findByTitle(String title);
	Optional<Blog> findById(String id);
	List<Blog> findByUsername(String username);
	Optional<Blog> findTopByIdAndUsernameOrderByIdDesc(String id, String username);

	
}