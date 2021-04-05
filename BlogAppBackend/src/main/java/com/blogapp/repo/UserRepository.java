package com.blogapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username);
	public Optional<User> findById(String Id);
	

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	 boolean existsById(String Id);


}