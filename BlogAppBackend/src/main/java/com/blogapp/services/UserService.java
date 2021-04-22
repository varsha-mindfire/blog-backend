package com.blogapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogapp.model.User;
import com.blogapp.repo.UserRepository;
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	   @Transactional(readOnly = true)
	    public User getUser(String id) {
	        Optional<User> user=userRepository.findById(id);
	        return user.get();
	    }

}
