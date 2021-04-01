package com.Blogapp.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Blogapp.model.Commentmodel;
@Repository
public interface Commentrepo extends MongoRepository<Commentmodel, String> {
	Optional<Commentmodel> findById(String id);
	

}