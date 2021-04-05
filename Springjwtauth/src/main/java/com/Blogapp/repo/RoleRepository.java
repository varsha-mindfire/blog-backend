package com.Blogapp.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Blogapp.model.Erole;
import com.Blogapp.model.Role;

//import com.blogapp.model.Erole;
//import com.blogapp.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(Erole name);
}