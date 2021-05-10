package com.blogapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blogapp.model.Blog;

//Blog repository interface
public interface BlogRepository extends MongoRepository<Blog, String> {

	List<Blog> findByTitle(String title);
	Optional<Blog> findById(String id);
	List<Blog> findByUsername(String username);
	Blog findByIdAndUsername(String id,String username);
	void deleteByIdAndUsername(String id,String username);
	List<Blog> findAllByOrderByCreateDateAsc();

	
}