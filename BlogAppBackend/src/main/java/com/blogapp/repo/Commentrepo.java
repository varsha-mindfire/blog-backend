package com.blogapp.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD:BlogAppBackend/src/main/java/com/blogapp/repo/Commentrepo.java
import com.blogapp.model.Commentmodel;
=======
import com.Blogapp.model.Comment;
>>>>>>> main:BlogAppBackend/src/main/java/com/blogapp/repo/CommentRepository.java
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
	Optional<Comment> findById(String id);
	

}