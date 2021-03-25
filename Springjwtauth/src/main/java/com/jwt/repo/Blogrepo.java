package com.jwt.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jwt.document.Blog;

@Repository
public interface Blogrepo extends MongoRepository<Blog, String> {
	public List<Blog> findBlogById(String Id);
	public List<Blog> findCourseByEmail(String email);

}
