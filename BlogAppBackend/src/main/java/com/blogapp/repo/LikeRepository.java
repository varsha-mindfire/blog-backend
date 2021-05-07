package com.blogapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blogapp.model.Like;

//Like repository interface
@Repository
public interface LikeRepository extends MongoRepository<Like, Integer>  {
	Integer findByLike(Integer Like);
	List<String> findUsernameByBlogId(String id);
	Optional<Like> findByBlogIdAndUsername(String blogId,String name);
	 Optional<Like> findTopByBlogIdAndUsernameOrderByIdDesc(String id, String username);
	 void deleteByUsernameAndBlogId(String username,String blogid);
	 void deleteByBlogId(String blogId);
}
