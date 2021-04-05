package com.blogapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

<<<<<<< HEAD:BlogAppBackend/src/main/java/com/blogapp/repo/Blogrepo.java
import com.blogapp.model.Blog;
public interface Blogrepo extends MongoRepository<Blog, String> {
=======
import com.Blogapp.model.Blog;
public interface BlogRepository extends MongoRepository<Blog, String> {
>>>>>>> main:BlogAppBackend/src/main/java/com/blogapp/repo/BlogRepository.java
	Optional<Blog> findByTitle(String title);
	Optional<Blog> findById(String id);
//	List<Blog> findByUsername(String username);
}
