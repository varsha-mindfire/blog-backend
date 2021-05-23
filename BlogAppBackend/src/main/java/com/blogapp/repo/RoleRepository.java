package com.blogapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapp.model.Erole;
import com.blogapp.model.Role;

/**
 * Role repository interface
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(Erole name);
}