package com.blogapp.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.blogapp.model.Like;

@Repository
public interface LikeRepository extends MongoRepository<Like, Integer>  {
	Integer findByLike(Integer Like);
	 Optional<Like> findTopByBlogIdAndUsernameOrderByIdDesc(String id, String username);
	 void deleteByUsername(String username);
}
