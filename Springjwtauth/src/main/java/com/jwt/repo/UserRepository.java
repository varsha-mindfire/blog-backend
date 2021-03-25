package com.jwt.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username);
	public Optional<User> findById(String Id);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	 boolean existsById(String Id);


}
